function validateName(name){
	if(name == ""){
		document.getElementById("name_msg").innerHTML = "<img src=\""+url+"/images/wrong.gif\">" + "<font color=\"red\">Ƶ�����Ʋ���Ϊ�գ�</font>" ;
		return false ;
	} else {
		document.getElementById("name_msg").innerHTML = "<img src=\""+url+"/images/right.gif\">" + "<font color=\"green\">Ƶ������������ȷ��</font>" ;
		return true ;
	}
}
function validateNote(note){
	if(note == ""){
		document.getElementById("note_msg").innerHTML = "<img src=\""+url+"/images/wrong.gif\">" + "<font color=\"red\">Ƶ����鲻��Ϊ�գ�</font>" ;
		return false ;
	} else {
		document.getElementById("note_msg").innerHTML = "<img src=\""+url+"/images/right.gif\">" + "<font color=\"green\">Ƶ�����������ȷ��</font>" ;
		return true ;
	}
}
function validate(f){
	return	validateName(f.name.value) && 
			validateNote(f.note.value);
}