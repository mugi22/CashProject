/**
 *combobox kelompok barang 
 */
	



	function addComboKelBarang(o,selectedKelBarang,selectedKodeBarang,cmdDest) {
			o.combobox({
				url : 'comboKelBarang.htm?' +"selected="+selectedKelBarang,
				valueField : 'id',
				textField : 'text',
				panelHeight:'auto',
				onSelect: function(rec) {	
					cmdDest.combobox('clear');
					urlk= 'comboKodeBarang.htm?kelBarang=' + /*$('#KodeKelompok')*/o.combobox('getValue')+"&selected="+selectedKodeBarang;
				    cmdDest.combobox('reload', urlk);
					}			
			});
			branchcode = '';
		}
/*
 * Combobox namaBarang dengan value kosong
 */
	function addComboKodeBarang(o,kelBarang,selected) {
			o.combobox({
				url : 'comboKodeBarang.htm?kelBarang=' + kelBarang+"&selected="+selected,
				valueField : 'id',
				textField : 'text',
				panelHeight:'auto'
			});
			branchcode = '';
		}
	


//lokasi cara pakai addComboLokasi($('#lokasi'),row.lokasi);
	function addComboLokasi(o,selected) {
		o.combobox({
			url : 'combolokasi.htm?'+"selected="+selected,
			valueField : 'id',
			textField : 'text',
			panelHeight:'auto'
		});
		branchcode = '';
	}













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


//tanggal
function getDateTime() {
    var now     = new Date(); 
    var year    = now.getFullYear();
    var month   = now.getMonth()+1; 
    var day     = now.getDate();
    var hour    = now.getHours();
    var minute  = now.getMinutes();
    var second  = now.getSeconds(); 
    if(month.toString().length == 1) {
        var month = '0'+month;
    }
    if(day.toString().length == 1) {
        var day = '0'+day;
    }   
    if(hour.toString().length == 1) {
        var hour = '0'+hour;
    }
    if(minute.toString().length == 1) {
        var minute = '0'+minute;
    }
    if(second.toString().length == 1) {
        var second = '0'+second;
    }   
    var dateTime = year+'/'+month+'/'+day+' '+hour+':'+minute+':'+second;   
     return dateTime;
}

//print
function cetak(){
	printDiv('printArea');
}

function printDiv(divID) {
    //Get the HTML of div
    var divElements = document.getElementById(divID).innerHTML;
    //Get the HTML of whole page
    var oldPage = document.body.innerHTML;

    //Reset the page's HTML with div's HTML only
    document.body.innerHTML = 
      "<html><head><title></title></head><body>" + 
      divElements + "</body>";

    //Print Page
    window.print();

    //Restore orignal HTML
    document.body.innerHTML = oldPage; 
}




