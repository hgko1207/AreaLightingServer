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

$(document).ready(function() {
	ApartmentManager.init();
	
	const cityTable = ApartmentManager.table();
	
	cityTable.on('select', function (e, dt, type, indexes) {
    	e.preventDefault();
        var rowData = cityTable.rows(indexes).data().toArray()[0];
        $('#apartment_card').removeClass('d-none');
        $('#danji_name').text(rowData.name + "의 단지 목록");
        
        const registButton = `<a href="${contextPath}/apartment/regist?id=${rowData.id}" 
        						class="btn bg-blue-400"><i class="icon-pencil5 mr-2"></i>단지 등록</a>`;
        
        $('#danji_regist').empty();
        $('#danji_regist').append(registButton);
        
        const div = `<div class="card-group-control card-group-control-left">
			<div class="card">
				<div class="card-header header-elements-inline">
					<h6 class="card-title">
						<a data-toggle="collapse" class="text-default" href="#collapsible-control-group1">마포 더샾</a>
					</h6>
					<div class="header-elements">
						<a th:href="@{/apartment/regist}" class="btn bg-green btn-block"><i class="icon-pencil5 mr-2"></i>층 등록</a>
					</div>
				</div>

				<div id="collapsible-control-group1" class="collapse show">
					<div class="card-body">
						<div>지하 1층 / 채널 번호 : 150</div>
					</div>
				</div>
			</div>
		</div>`;

		$("#apartment_body").empty();
		$("#apartment_body").append(div);
    }).on('deselect', function (e, dt, type, indexes) {
    	$('#apartment_card').addClass('d-none');
    });
	
	/*$("body").on("click", "#cityTable tbody tr", function (e) {
	    e.preventDefault();
	    var data = cityTable.row($(this).closest('tr')).data();
	    $("#danji_name").text(data.name + "의 단지 목록");
	    console.log(data);
	    
	    const div = `<div class="card-group-control card-group-control-left">
						<div class="card">
							<div class="card-header header-elements-inline">
								<h6 class="card-title">
									<a data-toggle="collapse" class="text-default" href="#collapsible-control-group1">마포 더샾</a>
								</h6>
								<div class="header-elements">
									<a th:href="@{/apartment/regist}" class="btn bg-green btn-block"><i class="icon-pencil5 mr-2"></i>층 등록</a>
								</div>
							</div>

							<div id="collapsible-control-group1" class="collapse show">
								<div class="card-body">
									<div>지하 1층 / 채널 번호 : 150</div>
								</div>
							</div>
						</div>
					</div>`;
	    
	    $("#apartment_body").empty();
	    $("#apartment_body").append(div);
	});*/
});
