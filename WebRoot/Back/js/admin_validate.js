function validateAdminName(name){
	if(name == ""){
		document.getElementById("adminid_msg").innerHTML = "<img src=\""+url+"/images/wrong.gif\">" + "<font color=\"red\">管理员名称不能为空！</font>" ;
		return false ;
	} else {
		document.getElementById("adminid_msg").innerHTML = "<img src=\""+url+"/images/right.gif\">" + "<font color=\"green\">管理员名称输入正确！</font>" ;
		return true ;
	}
}
function validateNote(note){
	if(note == ""){
		document.getElementById("note_msg").innerHTML = "<img src=\""+url+"/images/wrong.gif\">" + "<font color=\"red\">管理员组简介不能为空！</font>" ;
		return false ;
	} else {
		document.getElementById("note_msg").innerHTML = "<img src=\""+url+"/images/right.gif\">" + "<font color=\"green\">管理员组简介输入正确！</font>" ;
		return true ;
	}
}
function validatePassword(password){
	if(password == ""){
		document.getElementById("password_msg").innerHTML = "<img src=\""+url+"/images/wrong.gif\">" + "<font color=\"red\">密码不能为空！</font>" ;
		return false ;
	} else {
		document.getElementById("password_msg").innerHTML = "<img src=\""+url+"/images/right.gif\">" + "<font color=\"green\">密码输入正确！</font>" ;
		return true ;
	}
}
function validate(f){
	return	validateAdminName(f.adminName.value) && 
			validateNote(f.note.value);
}