$(document).ready(function(){
    InitDatePicker();
    updateAttendance();
    updateCase();
    // ==========================================================
    $("#attendance-save").click(function(){
      $.ajax({
        url:"/employee/attendance",
        type:"post",
        data:{
          "timestamp":$("#attendance-timestamp").val(),
          "note":$("#attendance-note").val()
        },
        success:()=>{
            updateAttendance();
            console.log("success");
        },
        error:()=>{
            console,log("error");
        }
      });
    });
});

function InitDatePicker(){
    var today = new Date();
    var startOfWeek = new Date(today);
    startOfWeek.setDate(today.getDate() - today.getDay());
    var endOfWeek = new Date(today);
    endOfWeek.setDate(today.getDate() + (6 - today.getDay()));
    $('#datepicker').datepicker({
      format: 'yyyy-mm-dd',
      startDate: startOfWeek,
      endDate: endOfWeek,
      autoclose: true,
      todayHighlight: true
    }).datepicker('setDate', new Date());
}

function updateAttendance(){
  const today = new Date();
  const start = new Date(today.setDate(today.getDate() - today.getDay()));
  const end = new Date(today.setDate(start.getDate() + 6));
  const formatter = new Intl.DateTimeFormat('en-CA', {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
  });
  $.ajax({
    url:"/employee/attendance",
    type:"get",
    data:{
      "start":formatter.format(start),
      "end":formatter.format(end)
    },
    success:(res)=>{
      res.content.forEach(item => {
        const date = new Date(item.localDateTime);
        const day = date.getDay();
        // console.log(`Username: ${item.username}, Day: ${day}`);
        $(".ems-attendance-tbody > tr").find("td").eq(day-1).text(item.note);
      });
    },
    error:()=>{
        console,log("error");
    }
  });
}

function updateCase(){
  $.ajax({
    url:"/employee/case",
    type:"get",
    success:(res)=>{
      res.content.forEach(item => {
        console.log(item);
        const row = $("<tr></tr>");
        let td = $("<td></td>").text(item.caseId);
        row.append(td);
        td = $("<td></td>").text(item.openTime);
        row.append(td);
        td = $("<td></td>").text(item.customer.name);
        row.append(td);
        td = $("<td></td>").text(item.username);
        row.append(td);
        td = $("<td></td>").text(item.status);
        row.append(td);
        td = $("<td></td>").text(item.description);
        row.append(td);
        $("#ems-case-content").append(row);
      });
    },
    error:()=>{
        console,log("error");
    }
  });
}