//$(document).ready(function() {
//	var tableClient = $('#usersList').DataTable({
//		"bProcessing": true,
//		"bServerSide": true,
//		//"sort": "position",
//		"bStateSave": false,
//		"iDisplayLength": 10,
//		"iDisplayStart": 0,
//		ajax: {
//			url: "account/all",
//			dataSrc: function(d){
//				console.log("dataSrc", d);
//				return d.data;
//			}
//		}
//	} );
//});


$(document).ready(function() {
	var tableClient = $('#usersList').DataTable({
		"bProcessing": true,
		"bServerSide": true,
		"bSort": false,
		"bStateSave": false,
		"iDisplayLength": 10,
		"iDisplayStart": 0,
		ajax: "account/all",
		"columns": [
			{ "data": "id" },
			{ "data": "email", "name": "Email"},
			{ "data": "role", "name": "Роль" },
			{ "data": "created" , "name": "Создана" }
		],
		"language" :{
			"processing": "Подождите...",
			"search": "Поиск по Email:",
			"lengthMenu": "Показать _MENU_ записей",
			"info": "Записи с _START_ до _END_ (_TOTAL_)",
			"infoEmpty": "Записи с 0 до 0 из 0 записей",
			"infoFiltered": "(отфильтровано из _MAX_ записей)",
			"infoPostFix": "",
			"loadingRecords": "Загрузка записей...",
			"zeroRecords": "Записи отсутствуют.",
			"emptyTable": "В таблице отсутствуют данные",
			"paginate": {
				"first": "Первая",
				"previous": "Предыдущая",
				"next": "Следующая",
				"last": "Последняя"
			},
			"aria": {
				"sortAscending": ": активировать для сортировки столбца по возрастанию",
				"sortDescending": ": активировать для сортировки столбца по убыванию"
			}
		}

	} );
});