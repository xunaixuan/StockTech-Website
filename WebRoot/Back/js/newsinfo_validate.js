function validateName(name){
	if(name == ""){
		document.getElementById("name_msg").innerHTML = "<img src=\""+url+"/images/wrong.gif\">" + "<font color=\"red\">���±��ⲻ��Ϊ�գ�</font>" ;
		return false ;
	} else {
		document.getElementById("name_msg").innerHTML = "<img src=\""+url+"/images/right.gif\">" + "<font color=\"green\">���±���������ȷ��</font>" ;
		return true ;
	}
}
function validateAuthor(name){
	if(name == ""){
		document.getElementById("author_msg").innerHTML = "<img src=\""+url+"/images/wrong.gif\">" + "<font color=\"red\">�������߲���Ϊ�գ�</font>" ;
		return false ;
	} else {
		document.getElementById("author_msg").innerHTML = "<img src=\""+url+"/images/right.gif\">" + "<font color=\"green\">��������������ȷ��</font>" ;
		return true ;
	}
}
function validateType(f){
	var flag = false;
	var check_array=document.getElementsByName("typeid");
	for(var i=0;i<check_array.length;i++){
        if(check_array[i].checked==true){         
           flag = true;
           break;
        }
    }
	
	if (!flag){
		alert("��ѡ������һ������");
	}
	return flag;
}
function validate(f){
	return	validateName(f.name.value) && 
			validateAuthor(f.author.value) && validateType(f);
}