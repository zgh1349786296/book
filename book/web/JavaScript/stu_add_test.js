function stu_add_test() {
    var id = document.Student_Add.stuId.value;
    var name = document.Student_Add.stuName.value;
    var pass = document.Student_Add.stuPassword.value;
    if (id.length != 10) {
        alert("学号填写不规范");
    } else if (pass.length < 6) {
        alert("密码长度不能小于6");
    } else if (name.length <= 0) {
        alert("名字不能为空");
    }else {
        document.Student_Add.submit();
    }
}