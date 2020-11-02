/*******************************************************************************************************
 * 위젯 공통 기능
*******************************************************************************************************/
const CommonWidget = function() {
	// Uniform
    var _componentUniform = function() {
    	if (!$().uniform) {
            console.warn('Warning - uniform.min.js is not loaded.');
            return;
        }

        $('.form-check-input-styled').uniform();
    }
	
	var _componentSelect2 = function() {
		if (!$().select2) {
        	console.warn('Warning - select2.min.js is not loaded.');
            return;
        }
    	
        var $select = $('.form-control-select2').select2({
            minimumResultsForSearch: Infinity,
            width: '100%',
            placeholder: 'Select a Data...'
        });
        
        $('.select2-size').select2({
            minimumResultsForSearch: Infinity,
            width: '90'
        });
        
        $('.select-search').select2();
        
        $select.on('change', function() {
            $(this).trigger('blur');
        });
    };
    
    var _componentJQuery = function() {
    	/** form 데이터들을 JSON 형식으로 변환 */
    	jQuery.fn.serializeObject = function() { 
    		var obj = null; 
    		try { 
    			if(this[0].tagName && this[0].tagName.toUpperCase() == "FORM" ) { 
				var arr = this.serializeArray(); 
				if(arr){ 
					obj = {}; 
					jQuery.each(arr, function() { 
						obj[this.name] = this.value; }); 
					} 
				} 
			} catch(e) { 
				alert(e.message); 
			} finally {} 
			return obj; 
		}
    };
    
    return {
        init: function() {
        	_componentUniform();
        	_componentSelect2();
        	_componentJQuery();
        }
    }
}();

document.addEventListener('DOMContentLoaded', function() {
	CommonWidget.init();
});

const swalInit = swal.mixin({
    buttonsStyling: false,
    confirmButtonClass: 'btn btn-primary',
    cancelButtonClass: 'btn btn-light'
});