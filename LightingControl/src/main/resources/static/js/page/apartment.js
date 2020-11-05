const ApartmentManager = function() {
	const DataTable = {
		ele: "#cityTable",
		table: null,
		option: {
			columns: [
				{ data: "name" },
			]
		},
		init: function() {
			this.table = Datatables.select(this.ele, this.option);
			this.search();
			return this.table;
		},
		search: function() {
			var param = new Object();
			Datatables.rowsAdd(this.table, contextPath + "/city/search", param);
		}
	}
	
	return {
		init: function() {
			DataTable.init();
		},
		table: function() {
			return DataTable.table;
		}
	}
}();

let cityId = 0;

$(document).ready(function() {
	ApartmentManager.init();
	
	const cityTable = ApartmentManager.table();
	
	cityTable.on('select', function (e, dt, type, indexes) {
    	e.preventDefault();
        var rowData = cityTable.rows(indexes).data().toArray()[0];
        cityId = rowData.id;
        
        $('#apartment_card').removeClass('d-none');
        $('#danji_name').text(rowData.name + "의 단지 목록");
        
        addData(cityId);
    }).on('deselect', function (e, dt, type, indexes) {
    	$('#apartment_card').addClass('d-none');
    });
});

function addData(cityId) {
	$('#danji_regist').empty();
    $("#apartment_body").empty();
    
    $.ajax({
		type: "GET",
       	url: contextPath + "/apartment/getList",
       	data: {"cityId": cityId},
       	success: function(response) {
       		const registButton = `<a href="${contextPath}/apartment/regist?id=${cityId}" 
    			class="btn bg-blue-400"><i class="icon-pencil5 mr-2"></i>단지 등록</a>`;
            $('#danji_regist').append(registButton);
            
            $.each(response, function(index, data) {
            	let div = `<div class="card-group-control card-group-control-left">
	    			<div class="card">
	    				<div class="card-header bg-transparent header-elements-inline">
	    					<h6 class="card-title">
	    						<a data-toggle="collapse" class="text-default font-weight-bold" href="#collapsible-control-group1">${data.name}</a>
	    					</h6>
	    					<div class="header-elements">
	    						<a href="${contextPath}/apartment/update?id=${data.id}" class="btn bg-success btn-sm">수정</a>
	    						<button type="button" onclick="deleteApartment(${data.id})" class="btn bg-danger btn-sm ml-2">삭제</button>
	    					</div>
	    				</div>
	    				
	    				<div id="collapsible-control-group1" class="collapse show">
	    					<div class="card-body">`;
            	
            	$.each(data.floors, function(index, floor) {
            		div += `<div>${floor.name} / 채널 번호 : ${floor.channel}</div>`;
            	});
            	
            	div += `</div></div></div></div>`;
            	
	    		$("#apartment_body").append(div);
            });
       	}
    });
}

function deleteApartment(id) {
	swalInit.fire({
        title: "선택한 단지를 삭제 하시겠습니까?",
        type: "warning",
        confirmButtonText: "확인",
        showCancelButton: true, 
        cancelButtonText: "취소",
    }).then(function(e) {
    	if (e.value) {
    		$.ajax({
        		url: contextPath + "/apartment/delete",
        		type: "DELETE",
        		data: { "id": id },
        		success: function(response) {
        			addData(cityId);
               	},
                error: function(response) {
                	swalInit.fire({title: "삭제를 실패하였습니다.", type: "error"})
                }
        	}); 
    	}
    });
}
