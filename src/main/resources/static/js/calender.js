/*document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
      //initialView: 'dayGridMonth',  // オプション　（月表示）
      timeZone: 'Asia/Tokyo',
      locale: 'ja',
      firstDay: 0,
      height : 'auto',
      headerToolbar: {
        left: "dayGridMonth",
        center: "title",
        right: "today prev,next"
      },
      buttonText: {
        today: '今月',
        month: '月',
        list: 'リスト'
      },
      dayCellContent: function(e) {
        e.dayNumberText = e.dayNumberText.replace('日', '');
      },

      
    });
    calendar.render();　// カレンダーの初期化（再レンダリング）
});*/