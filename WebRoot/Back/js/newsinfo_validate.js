function validateName(name){
	if(name == ""){
		document.getElementById("name_msg").innerHTML = "<img src=\""+url+"/images/wrong.gif\">" + "<font color=\"red\">文章标题不能为空！</font>" ;
		return false ;
	} else {
		document.getElementById("name_msg").innerHTML = "<img src=\""+url+"/images/right.gif\">" + "<font color=\"green\">文章标题输入正确！</font>" ;
		return true ;
	}
}
function validateAuthor(name){
	if(name == ""){
		document.getElementById("author_msg").innerHTML = "<img src=\""+url+"/images/wrong.gif\">" + "<font color=\"red\">文章作者不能为空！</font>" ;
		return false ;
	} else {
		document.getElementById("author_msg").innerHTML = "<img src=\""+url+"/images/right.gif\">" + "<font color=\"green\">文章作者输入正确！</font>" ;
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
		alert("请选择至少一个类型");
	}
	return flag;
}
function validate(f){
	return	validateName(f.name.value) && 
			validateAuthor(f.author.value) && validateType(f);
}