/**
 * 
 */




function addProvinsi(t) {
	alert("povonsi");
	t.combobox({
		url : 'comboProvinsi.htm?param=',
		valueField : 'id',
		textField : 'text',
		panelHeight:'200px'
	});
	
}





function upperCase(t) {
	t.textbox('textbox').bind('keyup', function(e) {
		$(this).val($(this).val().toUpperCase());
	});
}