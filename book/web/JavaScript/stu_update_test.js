function stu_update_test() {
    var id = document.Student_Update.stuId.value;
    var name = document.Student_Update.stuName.value;
    var pass = document.Student_Update.stuPassword.value;
    if (id.length != 10) {
        alert("学号填写不规范");
    } else if (name.length <= 0) {
        alert("名字不能为空");
    } else if (pass.length < 6) {
        alert("密码长度不能小于6");
    } else {
        document.Student_Update.submit();
    }
}