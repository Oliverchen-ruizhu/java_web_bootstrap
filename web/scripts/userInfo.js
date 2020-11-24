var TableEditable = function () {

    var handleTable = function () {
        function fn_load() {
            $.ajax({
                url:"/servlet/InitializeServlet",
                type:"post",
                async:false,
                success:function (result) {
                    $("#user_info").html(result);
                }
            })
        }

        var a = window.location.search;
        var temp = a.split("=");
        var username = decodeURIComponent(temp[1], true);
        $("#user").html("Welcome: "+username);
        fn_load();
        var userType="学生";//保存添加时选择的用户类型

        function restoreRow(oTable, nRow) {
            var aData = oTable.fnGetData(nRow);
            var jqTds = $('>td', nRow);

            for (var i = 0, iLen = jqTds.length; i < iLen; i++) {
                oTable.fnUpdate(aData[i], nRow, i, false);
            }
            oTable.fnDraw();
        }

        function editRow(oTable, nRow,flag) {
            var aData = oTable.fnGetData(nRow);
            var jqTds = $('>td', nRow);
            jqTds[1].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[1] + '">';
            jqTds[3].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[3] + '">';
            jqTds[4].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[4] + '">';
            jqTds[5].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[5] + '">';
            if (flag ==="edit"){
                jqTds[0].innerHTML = '<input type="text" class="form-control input-small" disabled="disabled" value="' + aData[0] + '">';
                jqTds[2].innerHTML = '<input type="text" class="form-control input-small" disabled="disabled" value="' + aData[2] + '">';
                jqTds[6].innerHTML = '<a class="edit" href="">Save</a>';
            } else {
                jqTds[0].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[0] + '">';
                jqTds[2].innerHTML = '<div class="btn-group dropdown">' +
                    ' <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" id="dropdown">' +
                    '  学生<span class="caret"></span>' +
                    ' </button>' +
                    '<input type="hidden" name="hidedrop_1" id="hidedrop_1" value="学生">' +
                    '  <ul class="dropdown-menu" id="type_list">' +
                    '    <li><a href="#" name="学生">学生</a></li>' +
                    '    <li role="separator" class="divider"></li>' +
                    '    <li><a href="#" name="老师">老师</a></li>' +
                    '    <li role="separator" class="divider"></li>' +
                    '    <li><a href="#" class="disabled" name="管理员">管理员</a></li>' +
                    '  </ul>' +
                    '</div>';
                $('.dropdown-toggle').dropdown();
                jqTds[6].innerHTML = '';
                $("#type_list li").on('click',function() {
                    userType=$(this).children(":first").attr("name");
                    $("#dropdown").html(userType+"<span class=\"caret\"></span>");
                    $("#hidedrop_1").attr("value",userType);
                });
            }
            jqTds[7].innerHTML = '<a class="cancel" href="">Cancel</a>';
        }

        function saveRow(oTable, nRow,tag) {
            var jqInputs = $('input', nRow);
            oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
            oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
            if (tag==="alter"){
                oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
            } else {
                oTable.fnUpdate(userType,nRow,2,false);
            }
            oTable.fnUpdate(jqInputs[3].value, nRow, 3, false);
            oTable.fnUpdate(jqInputs[4].value, nRow, 4, false);
            oTable.fnUpdate(jqInputs[5].value, nRow, 5, false);
            oTable.fnUpdate('<a class="edit" href="">Edit</a>', nRow, 6, false);
            oTable.fnUpdate('<a class="delete" href="">Delete</a>', nRow, 7, false);
            oTable.fnDraw();
        }

        var table = $('#sample_editable_1');

        var oTable = table.dataTable({

            // Uncomment below line("dom" parameter) to fix the dropdown overflow issue in the datatable cells. The default datatable layout
            // setup uses scrollable div(table-scrollable) with overflow:auto to enable vertical scroll(see: assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js).
            // So when dropdowns used the scrollable div should be removed.
            //"dom": "<'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r>t<'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>",

            "lengthMenu": [
                [5, 15, 20, -1],
                [5, 15, 20, "All"] // change per page values here
            ],

            // Or you can use remote translation file
            //"language": {
            //   url: '//cdn.datatables.net/plug-ins/3cfcc339e89/i18n/Portuguese.json'
            //},

            // set the initial value
            "pageLength": 10,

            "language": {
                "lengthMenu": " _MENU_ records"
            },
            "columnDefs": [{ // set default column settings
                'orderable': true,
                'targets': [0]
            }, {
                "searchable": true,
                "targets": [0]
            }],
            "order": [
                [0, "asc"]
            ] // set first column as a default sort by asc
        });

        var tableWrapper = $("#sample_editable_1_wrapper");

        tableWrapper.find(".dataTables_length select").select2({
            showSearchInput: true //hide search box with special css class
        }); // initialize select2 dropdown

        var nEditing = null;
        var nNew = false;
        //新增
        $('#sample_editable_1_new').click(function (e) {
            e.preventDefault();

                if (nNew && nEditing) {
                    var jqInputs = $('input', nEditing);
                    var datas= {
                        userAccount:jqInputs[0].value,
                        fullname : jqInputs[1].value,
                        user_type: userType,
                        phonnumber : jqInputs[3].value,
                        email : jqInputs[4].value,
                        address : jqInputs[5].value,
                    };
                    var isphone = /^[1][3,4,5,7,8,9][0-9]{9}$/;//检验手机号是否正确
                    var isemail =  /[_a-zA-Z\d\-\.]+@[_a-zA-Z\d\-]+(\.[_a-zA-Z\d\-]+)+$/;//检验是否为电子邮箱
                    if (datas.userAccount ===""||datas.fullname===""||datas.phonnumber===""||datas.email===""||datas.address===""){
                        alert("Free input values. Please check！");
                        oTable.fnDeleteRow(nEditing);
                    }
                    else if (!isphone.test(datas.phonnumber)){
                        alert("Mobile phone number fill in error！");
                        oTable.fnDeleteRow(nEditing);
                    }else if(!isemail.test(datas.email)){
                        alert("E-mail fill in error！");
                        oTable.fnDeleteRow(nEditing);
                    }else{
                        if (confirm("Do you want to Add it ?")) {
                            $.ajax({
                                url:"/servlet/AddServlet",
                                Type:"POST",
                                async:false,
                                data:datas,
                                success :function (result) {
                                    if (result==="200") {
                                        alert("Successfully added");
                                        saveRow(oTable, nEditing,"add");// save
                                        nEditing = null;
                                        nNew = false;
                                        return;
                                    }else{
                                        if(result ==="500"){
                                            alert("Network error！");
                                        }else if (result==="201") {
                                            alert("The current account has been registered!");
                                        }
                                        oTable.fnDeleteRow(nEditing); // cancel
                                        nEditing = null;
                                        nNew = false;
                                        return;
                                    }

                                }
                            })
                        } else {
                            oTable.fnDeleteRow(nEditing); // cancel
                            nEditing = null;
                            nNew = false;
                            return;
                        }
                    }
                }
            var aiNew = oTable.fnAddData(['', '', '', '', '', '','','']);
            var nRow = oTable.fnGetNodes(aiNew[0]);
            editRow(oTable, nRow,"add");
            nEditing = nRow;
            nNew = true;
        });
        //删除
        table.on('click', '.delete', function (e) {
            e.preventDefault();

            if (confirm("Are you sure to delete this row ?") == false) {
                return;
            }
            var userAccount =$(this).parents('tr').attr("value");//返回父节点对象的值
            var nRow = $(this).parents('tr')[0];
            $.ajax({
                url : "/servlet/DeleteServlet",
                Type :"POST",
                async : false,
                data :{
                    userAccount:userAccount
                },
                success :function (result) {
                    switch (result) {
                        case "200":
                            oTable.fnDeleteRow(nRow);//删除当前行
                            alert("Deleted Successfully!");
                            break;
                        case "500":
                            alert("Network error!");
                            break;
                    }
                }
            });
        });
        //取消
        table.on('click', '.cancel', function (e) {
            e.preventDefault();
            if (nNew) {
                oTable.fnDeleteRow(nEditing);
                nEditing = null;
                nNew = false;
            } else {
                restoreRow(oTable, nEditing);
                nEditing = null;
            }
        });
        //修改
        table.on('click', '.edit', function (e) {
            e.preventDefault();

            /* Get the row as a parent of the link that was clicked on */
            var nRow = $(this).parents('tr')[0];

            if (nEditing !== null && nEditing != nRow) {
                /* Currently editing - but not this row - restore the old before continuing to edit mode */
                restoreRow(oTable, nEditing);
                editRow(oTable, nRow);
                nEditing = nRow;
            } else if (nEditing == nRow && this.innerHTML == "Save") {
                /* Editing this row and want to save it */
                var jqInputs = $('input', nRow);
                var datas= {
                    userAccount:jqInputs[0].value,
                    fullname : jqInputs[1].value,
                    phonnumber : jqInputs[3].value,
                    email : jqInputs[4].value,
                    address : jqInputs[5].value,
                };
                $.ajax({
                   url:"/servlet/AlterServlet",
                    Type: "POST",
                    async: false,
                    data: datas,
                    success:function (result) {
                        switch (result) {
                            case "200":
                                saveRow(oTable, nEditing,"alter");
                                nEditing = null;
                                alert("update successfully！");
                                break;
                            case "500":
                                alert("Network error!");
                                break;
                        }
                    }
                });
            } else {
                /* No edit in progress - let's start one */
                editRow(oTable, nRow,"edit");
                nEditing = nRow;
            }
        });
    };
    var initializeTable = function () {
        $.ajax({
            url:"/servlet/InitializeServlet",
            type:"post",
            async:false,
            success:function (result) {
                $("#user_info").html(result);
            }
        })
    };
    return {

        //main function to initiate the module
        init: function () {
            //initializeTable();
            handleTable();
        }

    };

}();