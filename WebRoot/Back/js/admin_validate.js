function validateAdminName(name){
	if(name == ""){
		document.getElementById("adminid_msg").innerHTML = "<img src=\""+url+"/images/wrong.gif\">" + "<font color=\"red\">����Ա���Ʋ���Ϊ�գ�</font>" ;
		return false ;
	} else {
		document.getElementById("adminid_msg").innerHTML = "<img src=\""+url+"/images/right.gif\">" + "<font color=\"green\">����Ա����������ȷ��</font>" ;
		return true ;
	}
}
function validateNote(note){
	if(note == ""){
		document.getElementById("note_msg").innerHTML = "<img src=\""+url+"/images/wrong.gif\">" + "<font color=\"red\">����Ա���鲻��Ϊ�գ�</font>" ;
		return false ;
	} else {
		document.getElementById("note_msg").innerHTML = "<img src=\""+url+"/images/right.gif\">" + "<font color=\"green\">����Ա����������ȷ��</font>" ;
		return true ;
	}
}
function validatePassword(password){
	if(password == ""){
		document.getElementById("password_msg").innerHTML = "<img src=\""+url+"/images/wrong.gif\">" + "<font color=\"red\">���벻��Ϊ�գ�</font>" ;
		return false ;
	} else {
		document.getElementById("password_msg").innerHTML = "<img src=\""+url+"/images/right.gif\">" + "<font color=\"green\">����������ȷ��</font>" ;
		return true ;
	}
}
function validate(f){
	return	validateAdminName(f.adminName.value) && 
			validateNote(f.note.value);
}