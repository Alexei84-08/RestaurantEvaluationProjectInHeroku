function showModalsignIn() {
    $('.input').val('');
    var modal = $('#signInModal').modal('show');
    modal.on('shown.bs.modal', function () {
        $('#username').focus()
    });
}

function showModalUpdateRestaurant(data) {
    $('.input').val('');
    var modal = $('#updateRestaurantModal').modal('show');
    modal.on('shown.bs.modal', function () {
        $('#restaurantNameUpdate').focus()
    });
    populate('#formUpdateRestaurantModal', data);
}

function showModalUpdateUser(data) {
    $('.input').val('');
    var modal = $('#updateUserModal').modal('show');
    modal.on('shown.bs.modal', function () {
        $('#inputLoginUpdate').focus()
    });
    populate('#formUpdateUserModal', data);
}

function populate(frm, data) {
    $.each(data, function (key, value) {
        $('[name=' + key + ']', frm).val(value);
    });
}

function showModalAddUser() {
    $('.input').val('');
    var modal = $('#regUserModal').modal('show');
    modal.on('shown.bs.modal', function () {
        $('#inputLogin').focus()
    });
}

function showModalAddDish(restaurantId) {
    $('.input').val('');
    $('#restaurantId').val(restaurantId);
    var modal = $('#addDishModal').modal('show');
    modal.on('shown.bs.modal', function () {
        $('#dishName').focus()
    });
}

function showModalAddRestaurant() {
    $('.input').val('');
    var regUserModal = $('#addRestaurantModal').modal('show');
    regUserModal.on('shown.bs.modal', function () {
        $('#restaurantName').focus()
    });
}

function getRestaurantUpdate(id) {
    $.ajax({
        url: 'admin/restaurants/' + id,
        type: "GET",
        success: function (data) {
            showModalUpdateRestaurant(data);
        }
    });
}

function updateRestaurant() {
    var data = (JSON.stringify($('#formUpdateRestaurantModal').serializeObject()));
    $.ajax({
        url: 'admin/restaurants',
        type: "PUT",
        contentType: "application/json",
        data: data,
    }).done(function () {
        $('#updateRestaurantModal').modal('hide');
        successNoty('Данные обновлены!');
        getRestaurants();
    }).fail(function () {
    });
}

function addUser() {
    var data = (JSON.stringify($('#formRegUserModal').serializeObject()));
    $.ajax({
        url: '/admin/users',
        type: "POST",
        contentType: "application/json",
        data: data
    }).done(function () {
        $('#regUserModal').modal('hide');
        successNoty('Пользователь зарегистрирован!');
        getUsers();
    }).fail(function () {
    });
}

function updateUser() {
    var data = (JSON.stringify($('#formUpdateUserModal').serializeObject()));
    $.ajax({
        url: 'admin/users',
        type: "PUT",
        contentType: "application/json",
        data: data
    }).done(function () {
        $('#updateUserModal').modal('hide');
        successNoty('Данные обновлены!');
        getUsers()
    }).fail(function () {
    });
}

function delitUser(id) {
    $.ajax({
        url: '/admin/users' + '/' + id,
        type: "DELETE",
        data: {"id": id}
    }).done(function () {
        successNoty('Пользователь удален!');
        getUsers();
    }).fail(function () {
    });
}

function getUsers() {
    $.ajax({
        url: '/admin/users',
        type: "GET"
    }).done(function (data) {
        userTable(data);
    });
}

function getUserUpdate(id) {
    $.ajax({
        url: 'admin/user/' + id,
        type: "GET"
    }).done(function (data) {
        showModalUpdateUser(data);
    });
}

function userTable(data) {
    clearTable();
    var divTable = $('<div></div>').attr('id', 'divTable');
    var a = $('<a onclick="showModalAddUser()"></a>').text('Добавить').addClass('btn btn-info');
    var table = $('<table></table>').addClass('table table-dark table-hover table-bordered table-sm container text-center').attr('id', 'table');
    var thead = $('<thead><tr><th>ID</th><th>Имя</th><th>Email</th><th>Логин</th><th>Пароль</th><th class="nosort"></th><th class="nosort"></th></tr></thead>');
    var tbody = $('<tbody></tbody>');

    $('#tableUni').append(divTable);

    divTable.append(a);

    divTable.append(table);

    table.append(thead);

    $('#table').append(tbody);

    $.each(data, function (key, value) {
        tbody.append($('<tr>').append(
            $('<td>').text(value.id),
            $('<td>').text(value.name),
            $('<td>').text(value.email),
            $('<td>').text(value.login),
            $('<td>').text(value.password),
            $('<td>').append($('<a onclick="getUserUpdate(this.dataset.id)"></a>').text('Редактировать').attr("data-id", value.id).addClass('btn btn-info')),
            $('<td>').append($('<a onclick="delitUser(this.dataset.id)"></a>').text('Удалить').attr("data-id", value.id).addClass('btn btn-info'))
        ));
    });
    dataTable();
}

function addRestaurants() {
    var data = (JSON.stringify($('#formAddRestaurantModal').serializeObject()));

    $.ajax({
        url: '/admin/restaurants',
        type: "POST",
        contentType: "application/json",
        data: data
    }).done(function () {
        $('#addRestaurantModal').modal('hide');
        successNoty('Ресторан добавлен в базу');
        getRestaurants();
    }).fail(function () {
    });
}

function delitRestaurants(id) {
    $.ajax({
        url: '/admin/restaurants' + "/" + id,
        type: "DELETE",
        data: {"id": id}
    }).done(function () {
        successNoty('Ресторан удален!');
        getRestaurants();
    }).fail(function () {
    });
}

function getRestaurants() {
    $.ajax({
        url: '/admin/restaurants',
        type: "GET"
    }).done(function (data) {
        restaurantsTable(data);
    });
}

function restaurantsTable(data) {
    clearTable();
    var divTable = $('<div></div>').attr('id', 'divTable');
    var a = $('<a onclick="showModalAddRestaurant()"></a>').text('Добавить').addClass('btn btn-info');
    var table = $('<table></table>').addClass('table table-dark table-hover table-bordered table-sm container text-center').attr('id', 'table');
    var thead = $('<thead><tr><th>ID</th><th>Ресторан</th><th class="nosort"></th><th class="nosort"></th><th class="nosort"></th></tr></thead>');
    var tbody = $('<tbody></tbody>');

    $('#tableUni').append(divTable);

    divTable.append(a);

    divTable.append(table);

    table.append(thead);

    $('#table').append(tbody);

    $.each(data, function (key, value) {
        tbody.append($('<tr>').append(
            $('<td>').text(value.id),
            $('<td>').text(value.name),
            $('<td>').append($('<a onclick="getHistoryDishFromRestaurant(this.dataset.id)"></a>').text('Блюда').attr("data-id", value.id).addClass('btn btn-info')),
            $('<td>').append($('<a onclick="getRestaurantUpdate(this.dataset.id)"></a>').text('Редактировать').attr("data-id", value.id).addClass('btn btn-info')),
            $('<td>').append($('<a onclick="delitRestaurants(this.dataset.id)"></a>').text('Удалить').attr("data-id", value.id).addClass('btn btn-info'))
        ));
    });
    dataTable();
}

function getHistoryDishFromRestaurant(restoranId) {
    $.ajax({
        url: '/admin/restaurants/history/' + restoranId,
        type: "GET"
    }).done(function (data) {
        dishesTable(data, restoranId);
    });
}

function addDish(restoranId) {
    var data = (JSON.stringify($('#formAddDishModal').serializeObject()));
    $.ajax({
        url: '/admin/dishes',
        type: "POST",
        contentType: "application/json",
        data: data
    }).done(function () {
        $('#addDishModal').modal('hide');
        successNoty('Новая еда!');
        getHistoryDishFromRestaurant(restoranId);
    }).fail(function () {
    });
}

function delitDish(id, restoranId) {
    $.ajax({
        url: 'admin/dishes/' + id,
        type: "DELETE",
        data: {"id": id}
    }).done(function () {
        successNoty('Еда удалена!');
        getHistoryDishFromRestaurant(restoranId);
    }).fail(function () {
    });
}

function dishesTable(data, restoranId) {
    clearTable();
    var divTable = $('<div></div>').attr('id', 'divTable');
    var a = $('<a onclick="showModalAddDish(this.dataset.restoranid)"></a>').text('Добавить').attr("data-restoranid", restoranId).addClass('btn btn-info');
    var table = $('<table></table>').addClass('table table-dark table-hover table-bordered table-sm container text-center').attr('id', 'table');
    var thead = $('<thead><tr><th>Ресторан</th><th>ID</th><th>Название</th><th>Цена</th><th>Дата</th><th class="nosort"></th></tr></thead>');
    var tbody = $('<tbody></tbody>');

    $('#tableUni').append(divTable);

    divTable.append(a);

    divTable.append(table);

    table.append(thead);

    $('#table').append(tbody);

    $.each(data, function (key, value) {
        tbody.append($('<tr>').append(
            $('<td>').text('"' + value.restaurant.name + '"'),
            $('<td>').text(value.id),
            $('<td>').text(value.name),
            $('<td>').text(value.price + ' руб'),
            $('<td>').text(value.date.dayOfMonth + '/' + value.date.monthValue + '/' + value.date.year),
            $('<td>').append($('<a onclick="delitDish(this.dataset.id, this.dataset.restoranid)"></a>').text('Удалить').attr("data-id", value.id).attr("data-restoranid", restoranId).addClass('btn btn-info'))
        ));
    });
    dataTable();
}

function votesTable(data) {
    var thead = $('<thead><tr><th>Дата голосования</th><th>Ресторан</th></tr></thead>');
    var tbody = $('<tbody></tbody>');

    $.each(data, function (key, value) {
        tbody.append($('<tr>').append(
            $('<td>').text(value.dateTime.dayOfMonth + '/' + value.dateTime.monthValue + '/' + value.dateTime.year),
            $('<td>').text(value.restaurantName)
        ));
    });
    tablere(thead, tbody);
}

function getVotes() {
    $.ajax({
        url: '/user/votes',
        type: "GET"
    }).done(function (data) {
        votesTable(data);
    });
}

function addVotes(restaurantId) {
    var data = (JSON.stringify({"restaurantId": restaurantId}));
    $.ajax({
        url: '/user/votes',
        type: "POST",
        contentType: "application/json",
        data: data
    }).done(function () {
        successNoty('Вы проголосовали!');
    });
}

function getDayEstimate(date1) {
    var date = date1.toISOString().slice(0, 10);

    $.ajax({
        url: '/estimate',
        type: "GET",
        data: {date: date}
    }).done(function (data) {
        dayEstimateTable(data);
    }).fail(function () {
    });
}

function clearTable() {
    $('#divTable').remove();
}

$('#myModal').on('shown.bs.modal', function () {
    $('#inputLogin').focus()
});

$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

function dataTable() {
    $('#table').DataTable({

        // обратная сортировка
        // 'order': [[0, 'desc']],

        "oLanguage": {
            "sLengthMenu": "Показать _MENU_ записей на странице",
            "sZeroRecords": "Извините - ничего не найдено",
            "sInfo": "Показано с _START_ по _END_ из _TOTAL_ записей",
            "sInfoEmpty": "Нет записей",
            "sInfoFiltered": "(из _MAX_ записей)",
            "sSearch": "Поиск:",
            "oPaginate": {
                "sNext": "След. стр.",
                "sPrevious": "Пред. стр."
            }
        },

        //     'language':{
        //     "processing": "Подождите...",
        //     "search": "Поиск:",
        //     "lengthMenu": "Показать _MENU_ записей",
        //     "info": "Записи с _START_ до _END_ из _TOTAL_ записей",
        //     "infoEmpty": "Записи с 0 до 0 из 0 записей",
        //     "infoFiltered": "(отфильтровано из _MAX_ записей)",
        //     "infoPostFix": "",
        //     "loadingRecords": "Загрузка записей...",
        //     "zeroRecords": "Записи отсутствуют.",
        //     "emptyTable": "В таблице отсутствуют данные",
        //     "paginate": {
        //     "first": "Первая",
        //         "previous": "Предыдущая",
        //         "next": "Следующая",
        //         "last": "Последняя"
        // },
        //     "aria": {
        //     "sortAscending": ": активировать для сортировки столбца по возрастанию",
        //         "sortDescending": ": активировать для сортировки столбца по убыванию"
        // }
        // },

        'aoColumnDefs': [{
            'bSortable': false,
            'aTargets': ['nosort'],
        }]
    });
}

// $("#datetimepicker").datetimepicker({
//     inline: true,
//     changeYear: true,
//     changeMonth: true,
//     dateFormat: 'yy-mm-dd'
// });

// $.datetimepicker.setDefaults($.datetimepicker.regional['ru'] = {
//     closeText: 'Закрыть',
//     prevText: '&#x3c;Пред',
//     nextText: 'След&#x3e;',
//     currentText: 'Сегодня',
//     monthNames: ['Январь', 'Февраль', 'Март', 'Апрель', 'Май', 'Июнь',
//         'Июль', 'Август', 'Сентябрь', 'Октябрь', 'Ноябрь', 'Декабрь'],
//     monthNamesShort: ['Янв', 'Фев', 'Мар', 'Апр', 'Май', 'Июн',
//         'Июл', 'Авг', 'Сен', 'Окт', 'Ноя', 'Дек'],
//     dayNames: ['воскресенье', 'понедельник', 'вторник', 'среда', 'четверг', 'пятница', 'суббота'],
//     dayNamesShort: ['вск', 'пнд', 'втр', 'срд', 'чтв', 'птн', 'сбт'],
//     dayNamesMin: ['Вс', 'Пн', 'Вт', 'Ср', 'Чт', 'Пт', 'Сб'],
//     dateFormat: 'dd.mm.yy',
//     firstDay: 1,
//     isRTL: false
// });

$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

function submitForm() {
    var str = $("#signInModal").serialize();
    $.ajax({
        type: "POST",
        data: str,
        url: "/perform_login",
        dataType: "application/json",
    });
}

function tablere(thead, tbody) {
    var divTable = $('<div></div>').attr('id', 'divTable');
    var tablet = $('<table></table>').addClass('table table-dark table-hover table-bordered table-sm container text-center').attr('id', 'table');
// var thead = $('<thead></thead>').addClass('bg-success').attr('id', 'thead');
// var tbody = $('<tbody></tbody>').attr('id', 'tbody');

    clearTable();
    $('#tableUni').append(divTable);
    divTable.append(tablet);
    tablet.append(thead);
    tablet.append(tbody);
    dataTable();
}

function dayEstimateTable(data) {
    var thead = $('<thead><tr><th>Рейтинг</th><th>Ресторан</th><th>Голосов</th><th>Дата</th></tr></thead>');
    var tbody = $('<tbody></tbody>');

    var count = 0;
    $.map(data.dayVoteResult, function (key, value) {
        count++;
        var res = $.parseJSON(value);

        tbody.append($('<tr>').append(
            $('<td>').text(count),
            $('<td>').text(res.name),
            $('<td>').text(key),
            $('<td>').text(data.date.dayOfMonth + '-' + data.date.monthValue + '-' + data.date.year),
        ));
    });
    tablere(thead, tbody);
}


(function ($) {
    $.toggleShowPassword = function (options) {
        var settings = $.extend({
            field: '#password',
            control: '#toggle_show_password',
        }, options);

        var control = $(settings.control);
        var field = $(settings.field)

        control.bind('click', function () {
            if (control.is(':checked')) {
                field.attr('type', 'text');
            } else {
                field.attr('type', 'password');
            }
        })
    };
}(jQuery));

$.toggleShowPassword({
    field: '.bbq_license', // класс поля с паролем
    control: '.bbq_license_toggle' // класс переключателя
});


let failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(text) {
    closeNoty();
    new Noty({
        "text": text,
        theme: 'relax',
        "layout": "topRight",
        "type": 'success',
        "textAlign": "center",
        "easing": "swing",
        "animateOpen": {"height": "toggle"},
        "animateClose": {"height": "toggle"},
        "speed": "500", "timeout": "1000",
        "closable": false,
        "closeOnSelfClick": false
    }).show();
}

function failNoty(jqXHR) {
    closeNoty();
    // https://stackoverflow.com/questions/48229776
    const errorInfo = JSON.parse(jqXHR.responseText);
    failedNote = new Noty({
        text: "<span class='fa fa-lg fa-exclamation-circle'></span> &nbsp;" + errorInfo.url + "<br>"
            + errorInfo.type+"<br>"+ errorInfo.typeMessage+"<br>"+ errorInfo.details+"<br>",
        type: "error",
        layout: "topRight"
    }).show();
}



$(document).ajaxError(function (event, jqXHR, options, jsExc) {
    failNoty(jqXHR);
});

