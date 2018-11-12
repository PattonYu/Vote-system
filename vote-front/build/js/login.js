
/**
 * 验证表单
 * */
function checkForm(){
    var formData = $("#loginForm").serializeJson();
    console.log(formData);
    if (formData.username.trim() == ""){
        alert("用户名不能为空");
    }
    else if(formData.password.trim() == "" ){
        alert("密码不能为空");
    }else {
        login(formData);
    }
}

/**
 * 登录请求
 * */
function login(formData){

    $.ajax({
        url:"http://10.4.208.203/user/login",
        type:"post",
        data:{
            username:formData.username,
            password:formData.password,
            role:formData.role
        },
        async:false, // true 异步请求(默认)，false 同步请求
        dataType:"json",
        beforeSend:function () {
            // 在请求之前的操作
        },
        success:function (res) {
            // 请求成功后的操作，res是成功后的数据
            console.log(res);
            switch(res.code){
                case -1:
                        alert("系统出错");
                    break;
                case 0:
                    if (formData.role == 1){
                        location.href = "vote-system/vote-view.html";
                    } else if(formData.role == 0){
                        location.href = "admin/index.html";
                    }
                    break;
                case 10003:
                        alert("用户名或密码错误！");
                    break;
                case 10001:
                        alert("参数错误");
                    break;
            }
        },
        error:function (res) {
            // 请求失败后的操作，res是失败后的数据
            console.log(res);
            alert("登录出错，状态码："+res.status);
        }
    })
}

// 序列化表单，将其转化为json格式
$.fn.serializeJson = function() {
    var arr = this.serializeArray();
    var json = {};
    arr.forEach(function(item) {
        var name = item.name;
        var value = item.value;
        if (!json[name]) {
            json[name] = value;
        } else if ($.isArray(json[name])) {
            json[name].push(value);
        } else {
            json[name] = [json[name], value];
        }
    });
    return json;
}