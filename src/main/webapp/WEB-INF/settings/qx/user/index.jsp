<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/WEB-INF/include/commons_head.jsp" %>
    <script>
        jQuery(function ($) {
            // 初始化时间控件
            $("input.time").datetimepicker({
                language: "zh-CN",
                format: "yyyy-mm-dd hh:ii:ss",//显示格式
                minView: "hour", // 日期时间选择器所能够提供的最精确的时间选择视图。
                initialDate: new Date(),//初始化当前日期
                autoclose: true,//选中自动关闭
                todayBtn: true, //显示今日按钮
                clearBtn: true,
                pickerPosition: "bottom-right"
            });

            // 加载部门下拉框
            $.get("/dept/getDepts.json", function (data) {
                // data: [{部门1}, {部门2}, ...]
                $(data).each(function () {
                    //$("#create-dept").append('<option value="'+this.id+'">'+this.name+'</option>');

                    // 推荐语法
                    $("<option>", {
                        value: this.id,
                        text: this.name
                    }).appendTo("#create-dept");
                });
            });


            $("#saveBtn").click(function () {
                // 校验表单
                // 登录账号校验(非空、唯一性校验)
                var loginAct = $("#create-loginActNo").val();
                if (!loginAct) {
                    $.alert("登录名不能为空！");
                    return ;
                }

                $.ajax({
                    url: "/user/getExists.json?loginAct="+loginAct,
                    success: function (exists) {
                        if (exists) {
                            $.alert("登录名已存在！");
                        } else {
                            // 密码(非空、两次输入的密码是否一致)
                            var pwd = $("#create-loginPwd").val();
                            if (!pwd) {
                                $.alert("登录密码不能为空！");
                                return ;
                            }
                            var repwd = $("#create-confirmPwd").val();
                            if (!repwd) {
                                $.alert("确认密码不能为空！");
                                return ;
                            }

                            if ( pwd != repwd ) {
                                $.alert("两次密码输入不一致，请重新输入！");

                                // 清空两个密码，让用户重新输入，防止出错！
                                $("#create-loginPwd,#create-confirmPwd").val("");
                                return ;
                            }

                            // 部门验证（非空）
                            if(!$("#create-dept").val()) {
                                $.alert("请选择部门！");
                                return ;
                            }

                            // 所有验证通过，ajax提交表单数据
                            $.ajax({
                                url: "/user/save.do",
                                type: "post",
                                data: $("#userForm").formJSON(),
                                success: function (data) {
                                    if(data.success) {
                                        // 关闭模态窗口
                                        $("#createUserModal").modal("hide");

                                        $.alert("操作成功！");
                                        // 刷新列表...
                                    }
                                }
                            });
                        }
                    }
                });

            });
        });
    </script>
</head>
<body>

<!-- 创建用户的模态窗口 -->
<div class="modal fade" id="createUserModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">新增用户</h4>
            </div>
            <div class="modal-body">

                <form id="userForm" class="form-horizontal" role="form">

                    <div class="form-group">
                        <label for="create-loginActNo" class="col-sm-2 control-label">登录帐号<span
                                style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" name="loginAct" class="form-control" id="create-loginActNo">
                        </div>
                        <label for="create-username" class="col-sm-2 control-label">用户姓名</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" name="name" class="form-control" id="create-username">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="create-loginPwd" class="col-sm-2 control-label">登录密码<span
                                style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="password" name="loginPwd" class="form-control" id="create-loginPwd">
                        </div>
                        <label for="create-confirmPwd" class="col-sm-2 control-label">确认密码<span
                                style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="password" class="form-control" id="create-confirmPwd">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="create-email" class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" name="email" class="form-control" id="create-email">
                        </div>
                        <label for="create-expireTime" class="col-sm-2 control-label">失效时间</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" name="expireTime" style="cursor: text" class="form-control time" readonly id="create-expireTime">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="create-lockStatus" class="col-sm-2 control-label">锁定状态</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <select name="lockStatus" class="form-control" id="create-lockStatus">
                                <option value="">--请选择--</option>
                                <option value="1">启用</option>
                                <option value="0">锁定</option>
                            </select>
                        </div>
                        <label for="create-dept" class="col-sm-2 control-label">部门<span
                                style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <select name="deptId" class="form-control" id="create-dept">
                                <option value="">--请选择--</option>
                                <%--部门需要使用ajax加载--%>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="create-allowIps" class="col-sm-2 control-label">允许访问的IP</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" name="allowIps" class="form-control" id="create-allowIps" style="width: 280%"
                                   placeholder="多个用逗号隔开">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="saveBtn" type="button" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>


<div>
    <div style="position: relative; left: 30px; top: -10px;">
        <div class="page-header">
            <h3>用户列表</h3>
        </div>
    </div>
</div>

<div class="btn-toolbar" role="toolbar" style="position: relative; height: 80px; left: 30px; top: -10px;">
    <form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">

        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">用户姓名</div>
                <input class="form-control" type="text">
            </div>
        </div>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">部门名称</div>
                <input class="form-control" type="text">
            </div>
        </div>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">锁定状态</div>
                <select class="form-control">
                    <option></option>
                    <option>锁定</option>
                    <option>启用</option>
                </select>
            </div>
        </div>
        <br><br>

        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">失效时间</div>
                <input class="form-control" type="text" id="startTime"/>
            </div>
        </div>

        ~

        <div class="form-group">
            <div class="input-group">
                <input class="form-control" type="text" id="endTime"/>
            </div>
        </div>

        <button type="submit" class="btn btn-default">查询</button>

    </form>
</div>


<div class="btn-toolbar" role="toolbar"
     style="background-color: #F7F7F7; height: 50px; position: relative;left: 30px; width: 110%; top: 20px;">
    <div class="btn-group" style="position: relative; top: 18%;">
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#createUserModal"><span
                class="glyphicon glyphicon-plus"></span> 创建
        </button>
        <button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button>
    </div>
    <div class="btn-group" style="position: relative; top: 18%; left: 5px;">
        <button type="button" class="btn btn-default">设置显示字段</button>
        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
            <span class="caret"></span>
            <span class="sr-only">Toggle Dropdown</span>
        </button>
        <ul id="definedColumns" class="dropdown-menu" role="menu">
            <li><a href="javascript:void(0);"><input type="checkbox"/> 登录帐号</a></li>
            <li><a href="javascript:void(0);"><input type="checkbox"/> 用户姓名</a></li>
            <li><a href="javascript:void(0);"><input type="checkbox"/> 部门名称</a></li>
            <li><a href="javascript:void(0);"><input type="checkbox"/> 邮箱</a></li>
            <li><a href="javascript:void(0);"><input type="checkbox"/> 失效时间</a></li>
            <li><a href="javascript:void(0);"><input type="checkbox"/> 允许访问IP</a></li>
            <li><a href="javascript:void(0);"><input type="checkbox"/> 锁定状态</a></li>
            <li><a href="javascript:void(0);"><input type="checkbox"/> 创建者</a></li>
            <li><a href="javascript:void(0);"><input type="checkbox"/> 创建时间</a></li>
            <li><a href="javascript:void(0);"><input type="checkbox"/> 修改者</a></li>
            <li><a href="javascript:void(0);"><input type="checkbox"/> 修改时间</a></li>
        </ul>
    </div>
</div>

<div style="position: relative; left: 30px; top: 40px; width: 110%">
    <table class="table table-hover">
        <thead>
        <tr style="color: #B3B3B3;">
            <td><input type="checkbox"/></td>
            <td>序号</td>
            <td>登录帐号</td>
            <td>用户姓名</td>
            <td>部门名称</td>
            <td>邮箱</td>
            <td>失效时间</td>
            <td>允许访问IP</td>
            <td>锁定状态</td>
            <td>创建者</td>
            <td>创建时间</td>
            <td>修改者</td>
            <td>修改时间</td>
        </tr>
        </thead>
        <tbody>
        <tr class="active">
            <td><input type="checkbox"/></td>
            <td>1</td>
            <td><a href="detail.html">zhangsan</a></td>
            <td>张三</td>
            <td>市场部</td>
            <td>zhangsan@bjpowernode.com</td>
            <td>2017-02-14 10:10:10</td>
            <td>127.0.0.1,192.168.100.2</td>
            <td><a href="javascript:void(0);" onclick="window.confirm('您确定要锁定该用户吗？');"
                   style="text-decoration: none;">启用</a></td>
            <td>admin</td>
            <td>2017-02-10 10:10:10</td>
            <td>admin</td>
            <td>2017-02-10 20:10:10</td>
        </tr>
        <tr>
            <td><input type="checkbox"/></td>
            <td>2</td>
            <td><a href="detail.html">lisi</a></td>
            <td>李四</td>
            <td>市场部</td>
            <td>lisi@bjpowernode.com</td>
            <td>2017-02-14 10:10:10</td>
            <td>127.0.0.1,192.168.100.2</td>
            <td><a href="javascript:void(0);" onclick="window.confirm('您确定要启用该用户吗？');"
                   style="text-decoration: none;">锁定</a></td>
            <td>admin</td>
            <td>2017-02-10 10:10:10</td>
            <td>admin</td>
            <td>2017-02-10 20:10:10</td>
        </tr>
        </tbody>
    </table>
</div>

<div style="height: 50px; position: relative;top: 30px; left: 30px;">
    <div>
        <button type="button" class="btn btn-default" style="cursor: default;">共<b>50</b>条记录</button>
    </div>
    <div class="btn-group" style="position: relative;top: -34px; left: 110px;">
        <button type="button" class="btn btn-default" style="cursor: default;">显示</button>
        <div class="btn-group">
            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                10
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" role="menu">
                <li><a href="#">20</a></li>
                <li><a href="#">30</a></li>
            </ul>
        </div>
        <button type="button" class="btn btn-default" style="cursor: default;">条/页</button>
    </div>
    <div style="position: relative;top: -88px; left: 285px;">
        <nav>
            <ul class="pagination">
                <li class="disabled"><a href="#">首页</a></li>
                <li class="disabled"><a href="#">上一页</a></li>
                <li class="active"><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li><a href="#">下一页</a></li>
                <li class="disabled"><a href="#">末页</a></li>
            </ul>
        </nav>
    </div>
</div>

</body>
</html>