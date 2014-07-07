function validateName(name){
	if(name == ""){
		document.getElementById("name_msg").innerHTML = "<img src=\""+url+"/images/wrong.gif\">" + "<font color=\"red\">频道名称不能为空！</font>" ;
		return false ;
	} else {
		document.getElementById("name_msg").innerHTML = "<img src=\""+url+"/images/right.gif\">" + "<font color=\"green\">频道名称输入正确！</font>" ;
		return true ;
	}
}
function validateNote(note){
	if(note == ""){
		document.getElementById("note_msg").innerHTML = "<img src=\""+url+"/images/wrong.gif\">" + "<font color=\"red\">频道简介不能为空！</font>" ;
		return false ;
	} else {
		document.getElementById("note_msg").innerHTML = "<img src=\""+url+"/images/right.gif\">" + "<font color=\"green\">频道简介输入正确！</font>" ;
		return true ;
	}
}
function validate(f){
	return	validateName(f.name.value) && 
			validateNote(f.note.value);
}