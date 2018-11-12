
var PAGING_DATA = {
    PAGE:1, //默认的当前页码
    LIMIT:10    // 默认的每页显示数量
};
var userList = []; // 存储当前页码用户的数据

// 页面文档准备完毕后执行
$(function () {
    getUserByPaging();
});
/**
 * 分页查询用户信息
 */
function getUserByPaging() {
    $.ajax({
        url:"http://10.4.208.203/admin/getUser",
        type:"get",
        data:{
            page:PAGING_DATA.PAGE,
            limit:PAGING_DATA.LIMIT
        },
        async:false,
        dataType:"json",
        success:function (res) {
                    console.log(res);
                    switch(res.code){
                        case -1:
                            alert("系统出错");
                            break;
                        case 0:
                            // 存储获取到的当前用户数据
                            userList = res.data.users;
                            // 渲染用户表格
                            renderUserTable(res.data);
                            break;
                        case 10001:
                            alert("参数错误");
                    break;
            }
        },
        error:function (res) {
            console.log(res);
            alert("出错了，状态码："+res.status);
        }
    })
}

/**
 * 渲染用户列表函数
 * @param data 得到的用户对象
 **/
function renderUserTable(data){
    $("#userTableBody").empty(); // 清除userTableBody的所有子DOM
    for (var i = 0; i < data.users.length; i++) {
        // 在userTableBody的尾部追加内容（DOM）
        $("#userTableBody").append("<tr>\n" +
            "<td>"+data.users[i].id+"</td>\n" +
            "<td>"+data.users[i].username+"</td>\n" +
            "<td>"+data.users[i].realName+"</td>\n" +
            "<td>"+data.users[i].email+"</td>\n" +
            "<td>\n" +
            "<button type=\"button\" class=\"btn btn-warning btn-sm\" onclick='goToUserUpdataView("+i+")'>修改</button>\n" +
            "<button type=\"button\" class=\"btn btn-danger btn-sm\" onclick='deleteUser("+i+")'>删除</button>\n" +
            "</td>\n" +
            "</tr>");
    }
}
function goToUserUpdataView(index) {
    window.location.href="../../pages/admin/updataUser.html?id="+userList[index].id+"?email"+userList[index].email;
}
function checkUserData() {
    var updata = $("#updataUser").serializeJson();
    console.log(updata);
    if(updata.newRealName.trim()==""){
        alert("真实姓名不能为空");
    }else if(updata.newPassword2.trim()==""){
        alert("密码不能为空");
    }else if (updata.newPassword1.trim() != updata.newPassword2.trim()){
        alert("两次密码不一致");
    }
    else {if(updata.newEmail==""){
        updata.newEmail=getUrlData().email;
    }
        savaUserData(updata);
    }
}

function savaUserData(updata) {
    $.ajax({ url:"http://10.4.208.203/admin/updateUser",
        type:"post",
        data:{
            uid:getUrlData().id,
            password: updata.newPassword2,
            realName:updata.newRealName,
            email: updata.newEmail
        },
        async:true,
        dataType:"json",
        success:function (res) {
            // 请求成功后的操作，res是成功后的数据
            console.log(res);
            switch(res.code){
                case -1:
                    alert("系统出错");
                    break;
                case 0:
                    alert("修改成功");
                    window.location.href="../../pages/admin/userManage.html";
                    break;
                case 10001:
                    alert("参数错误");
                    break;
            }
        },
        error:function (res) {
            // 请求失败后的操作，res是失败后的数据
            console.log(res);
            alert("修改失败！"+res.status);
        }
    })

}


/**
 * 删除某个用户
 * @param index 当前用户的索引
 */
function deleteUser(index) {
    var D = confirm("确认删除？");
    if(D==true){
        $.ajax({ url:"http://10.4.208.203/admin/deleteUserById",
        type:"post",
        data:{
            uid:userList[index].id,
        },
        async:false,
        dataType:"json",
        success:function (res) {
            // 请求成功后的操作，res是成功后的数据
            console.log("你点击了删除按钮，并且删除的用户是：")
            console.log(userList[index]);
            location.reload();
            console.log(res);
            switch(res.code){
                case -1:
                    alert("系统出错");
                    break;
                case 0:
                    alert("删除成功");
                    break;
                case 10003:
                    alert("用户不存在");
                    break;
                case 10001:
                    alert("参数错误");
                    break;
            }
        },
        error:function (res) {
            // 请求失败后的操作，res是失败后的数据
            console.log(res);
            alert("删除失败！"+res.status);
        }
    })}
}

/**
* 添加用户
* */
function checkInfo() {
    var formDate = $("#addUser").serializeJson();//接受添加的信息
    console.log(formDate);
    if (formDate.username == ""){
        alert("用户名不能为空");
    }else if(formDate.password == "" ){
        alert("密码不能为空");
    }else {
        addUser(formDate);
    }
}

function addUser(formDate) {
    $.ajax({ url:"http://10.4.208.203/admin/addUser",
        type:"post",
        data:{
            username:formDate.username,
            password:formDate.password,
            realName:formDate.realname,
            email:formDate.email
        },
        async:false,
        dataType:"json",
        success:function (res) {
            // 请求成功后的操作，res是成功后的数据
            console.log(res);
            switch(res.code){
                case -1:
                    alert("系统出错");
                    break;
                case 0:
                    alert("添加成功");
                    location.reload();
                    break;
                case 10004:
                    alert("用户已存在");
                    break;
                case 10001:
                    alert("参数错误");
                    break;
            }
        },
        error:function (res) {
            // 请求失败后的操作，res是失败后的数据
            console.log(res);
            alert("添加失败！"+res.status);
        }
    })
}

// /**
//  * 根据ID修改用户信息：用户名/邮箱/密码
//  * **/
// function checkUpDataInfo() {
//     var UserInfo = $("#modifide").serializeJson();
//     if(UserInfo.newPassword1.trim()!=UserInfo.newPassword2.trim()){
//         alert("两次密码输入不一致！");
//     }
//     else{
//         sendUserInfo(UserInfo);
//     }
// }
//
// function sendUserInfo(UserInfo) {
//
//     $.ajax({ url:"http://10.4.208.203/admin/addUser",
//         type:"post",
//         data:{
//             uid:userList[UserInfo].id,
//             password:userList[UserInfo].newPassword1,
//             realName:userList[UserInfo].newRealName,
//             email:userList[UserInfo].newEmail
//         },
//         async:false,
//         dataType:"json",
//     })
// }
var playerList = []; // 存储当前页码选手的数据

// 页面文档准备完毕后执行
$(function () {
    getPlayerByPaging();
});
/**
 * 分页查询选手信息
 */
function getPlayerByPaging() {
    $.ajax({
        url:"http://10.4.208.203/vote/getPlayer",
        type:"get",
        data:{
            page:PAGING_DATA.PAGE,
            limit:PAGING_DATA.LIMIT
        },
        async:false,
        dataType:"json",
        success:function (res) {
            console.log(res);
            switch(res.code){
                case -1:
                    alert("系统出错");
                    break;
                case 0:
                    // 存储获取到的当前选手数据
                    playerList = res.data.players;
                    // 渲染选手表格
                    renderPlayerTable(res.data);
                    break;
                case 10001:
                    alert("参数错误");
                    break;
            }
        },
        error:function (res) {
            console.log(res);
            alert("出错了，状态码："+res.status);
        }
    })
}

/**
 * 渲染选手列表函数
 * @param data 得到的选手对象
 **/
var pid;
function renderPlayerTable(data){
    $("#playerTableBody").empty(); // 清除userTableBody的所有子DOM
    for (var i = 0; i < data.players.length; i++) {
        // 在playerTableBody的尾部追加内容（DOM）
        $("#playerTableBody").append("<tr>\n" +
            "<td>"+data.players[i].id+"</td>\n" +
            "<td>"+data.players[i].realName+"</td>\n" +
            "<td>"+data.players[i].profile+"</td>\n" +
            "<td>\n" +
            "<button type=\"button\" class=\"btn btn-warning btn-sm\" onclick='goToPlayerUpdataView("+i+")'>修改</button>\n" +
            "<button type=\"button\" class=\"btn btn-danger btn-sm\" onclick='deletePlayer("+i+")'>删除</button>\n" +
            "</td>\n" +
            "</tr>");
    }
}
function goToPlayerUpdataView(index) {
    window.location.href="../../pages/admin/updataPlayer.html?id="+playerList[index].id;

}
function checkSavaData() {
    var updata = $("#updataPlayer").serializeJson();
    console.log(updata);
    savaData(updata);
}
function savaData(updata) {
    $.ajax({ url:"http://10.4.208.203/vote/updatePlayer",
        type:"post",
        data:{
            pid:getUrlData().id,
            realName:updata.newRealName,
            profile:updata.newProfile
        },
        async:true,
        dataType:"json",
        success:function (res) {
            // 请求成功后的操作，res是成功后的数据
            console.log(res);
            switch(res.code){
                case -1:
                    alert("系统出错");
                    break;
                case 0:
                    alert("修改成功");
                    window.location.href="../../pages/admin/playerManage.html";
                    break;
                case 10004:
                    alert("用户已存在");
                    break;
                case 10001:
                    alert("参数错误");
                    break;
            }
        },
        error:function (res) {
            // 请求失败后的操作，res是失败后的数据
            console.log(res);
            alert("修改失败！"+res.status);
        }
    })

}


/**
 * 删除某个选手
 * @param index 当前选手的索引
 */
function deletePlayer(index) {
    var D = confirm("确认删除？");
    if(D==true){
        $.ajax({ url:"http://10.4.208.203/vote/deletePlayerById",
            type:"post",
            data:{
                pid:playerList[index].id,
            },
            async:false,
            dataType:"json",
            success:function (res) {
                // 请求成功后的操作，res是成功后的数据
                console.log("你点击了删除按钮，并且删除的用户是：")
                console.log(playerList[index]);
                location.reload();
                console.log(res);
                switch(res.code){
                    case -1:
                        alert("系统出错");
                        break;
                    case 0:
                        alert("删除成功");
                        break;
                    case 10003:
                        alert("用户不存在");
                        break;
                    case 10001:
                        alert("参数错误");
                        break;
                }
            },
            error:function (res) {
                // 请求失败后的操作，res是失败后的数据
                console.log(res);
                alert("删除失败！"+res.status);
            }
        })}
}

/**
 * 添加选手
 * */
function checkInfo1() {
    var formDate = $("#addPlayer").serializeJson();//接受添加的信息
    console.log(formDate);
    if (formDate.playername == ""){
        alert("选手姓名不能为空");
    }else if(formDate.profiles == "" ){
        alert("详情不能为空");
    }else {
        addPlayer(formDate);
    }
}

function addPlayer(formDate) {
    $.ajax({ url:"http://10.4.208.203/vote/addPlayer",
        type:"post",
        data:{
            realName:formDate.playername,
            profile:formDate.profiles
        },
        async:false,
        dataType:"json",
        success:function (res) {
            // 请求成功后的操作，res是成功后的数据
            console.log(res);
            switch(res.code){
                case -1:
                    alert("系统出错");
                    break;
                case 0:
                    alert("添加成功");
                    location.reload();
                    break;
                case 10004:
                    alert("用户已存在");
                    break;
                case 10001:
                    alert("参数错误");
                    break;
            }
        },
        error:function (res) {
            // 请求失败后的操作，res是失败后的数据
            console.log(res);
            alert("添加失败！"+res.status);
        }
    })
}


/**
 * 获取参数地址
 */
function getUrlData(){
    var dataList = location.href.split("?")[1].split("&");
    var data = {};
    for (var i = 0; i < dataList.length; i++) {
        data[ dataList[i].split("=")[0]] =  dataList[i].split("=")[1];
    }
    return data;
}

// function change(obj){
//     /*
//     一：文件名变成文本框编辑
//     二：修改文件名变成两个按钮【保存，取消】
//     */
// //一
//     i
// //获取<a>标签父亲父亲节点<td>的兄弟节点<td>
//     var p = $(obj).parent().parent().prev();
// //获取<td>标签的属性值
//     var title = p.text();
// //替换<td>标签
//     $(p).html("<form id='updatePlayer'><input type='text' name='newPlayerName' placeholder='修改姓名'>" +
//         "<input type='text' name='newPlayerProfile' placeholder='新详情'></form>");
//     //二
// //获得<a>标签所在的父亲节点<td>
//     var o = $(obj).parent();
// //获取
//     $(o).html("<input type=\"button\" class='btn-info' value=\"保存\" onclick=\"checkSavaData();\"/><input type=\"button\" class='btn-info' value=\"取消\" onclick=\"cancleEdit(this,'"+title+"')\" //>");
//
// };
// //取消编辑、恢复成原来
// function cancleEdit(obj,data){
//     var p = $(obj).parent().parent().prev();
//     $(p).html(data);
//     var o = $(obj).parent();
//     $(o).html("<a onclick=\"change(this)\" hrefgr=\"javascript:void(0);\">修改</a>");
// }


/**
 * 序列化表单，将其转化为json格式
 */
$.fn.serializeJson = function() {
    var arr = this.serializeArray();
    var json = {};
    arr.forEach(function (item) {
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

// function saveData(obj, data) {
// //获取修改后的值
//     var p = $(obj).parent().parent().prev().find("input").val();
//     var op = data;
//     var flag = 3;
// //通过ajax处理
//     $.ajax({
//         url : "http://10.4.208.203/admin/updataUser",
//         type : "POST",
//         data : {
//             "uid" :userList[data].id,
//             "realName":userList[data].realName,
//             "email" :userList[data].email,
//         },
//         dataType : "text",
//         success : function(data) {
// //将返回的JSON字符串转换成JSON对象
//             var b = eval("(" + data + ")");
//             var c = b.url;
// //对结果进行处理
//             if (c == "success") {
//                 cancleEdit(obj, p);
//             } else {
//                 alert("对不起，已存在相同名字的文件！");
//             }
//         }
//     });
// }
