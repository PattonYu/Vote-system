var playerList = []; // 存储当前页码选手的数据
var PAGING_DATA = {
    PAGE:1, //默认的当前页码
    LIMIT:10    // 默认的每页显示数量
};
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
            "<td>"+"<input type='radio' name='playerName' id='player' onclick='getPid("+i+")'>"+"<label for='player'>"+data.players[i].realName+"</label>"+"</td>\n"+
            "</tr>");

    }

}
function getPid(index){
    pid=index;
}
function submitData(){
    var D = confirm("确认提交？");
    if (D){   $.ajax({
        url:"http://10.4.208.203/vote/getVote",
        type:"post",
        data:{
            pid:playerList[pid].id
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
                    alert("提交成功")
                    window.location.href="../../pages/vote-system/success-vote.html";
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
    })}

}