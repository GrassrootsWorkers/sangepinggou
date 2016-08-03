function yywAlert(msg){
	if(typeof($('#alertModal').html())!="undefined"){
		$('#alertModal').find("h4").html(msg);
		$('#alertModal').modal(null);
	}else{
		var html="";
		html += '<div class="modal fade" id="alertModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">';
		html += '    <div class="modal-dialog">                                                                                     ';
		html += '        <div class="modal-content">                                                                                ';
		html += '            <div class="modal-header text-center">                                                                 ';
		html += '                <h4 class="modal-title" id="myModalLabel">'+msg+'</h4>                                    ';
		html += '            </div>                                                                                                 ';
		html += '            <div class="modal-footer text-center">                                                                 ';
		html += '                <a href="javascript:void(0);" class="btn btn-primary btn-danger" data-dismiss="modal">È·¶¨</a>                              ';
		html += '            </div>                                                                                                 ';
		html += '        </div>                                                                                                     ';
		html += '    </div>                                                                                                         ';
		html += '</div>                                                                                                             ';
		$("body").append( html );
		$('#alertModal').modal(null);
	}
}