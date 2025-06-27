$(document).ready(function(){
  loadEmployee();

  $("#employee-save").click(function(){
    $.ajax({
      url:"/api/admin/update/employee",
      type:"post",
      data:{
        "username":$("#modal-username").text(),
        "name":$("#modal-name").val(),
        "role":$("#modal-role").val(),
        "password":$("#modal-password").val().trim()
      },
      success:()=>{
        loadEmployee();
        console.log("success");
      },
      error:()=>{
          console,log("error");
      }
    });
  });
});

function loadEmployee(){
  $.ajax({
    url:"/api/admin/employee",
    type:"get",
    success:(res)=>{
      $("#ems-employee-content").empty();
      res.content.forEach(item => {
        const row = $("<tr></tr>");
        let td = $("<td></td>").text(item.name);
        row.append(td);
        td = $("<td></td>").text(item.role);
        row.append(td);
        td = $("<td></td>").text(item.username);
        row.append(td);
        td = $("<td></td>").text(item.lastLogin);
        row.append(td);
        const btn = $("<a class=\"templatemo-edit-btn\">Edit</a>");
        btn.click(function(){
          $("#modal-title-username").text(item.username);
          $("#modal-username").text(item.username);
          $("#modal-name").val(item.name);
          $("#modal-role").val(item.role);
          $("#modal-lastLogin").text(item.lastLogin);
          $("#modal-employee").modal("show");
        });
        td = $("<td></td>").append(btn);
        row.append(td);
        $("#ems-employee-content").append(row);
      });
    },
    error:()=>{
        console,log("error");
    }
  });
}
