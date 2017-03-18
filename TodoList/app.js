var itemTemplate = $('#templates .item')
var list = $('#list')

var addItemToPage = function(itemData) {
	var item = itemTemplate.clone()
	item.attr('data-id', itemData.id)
	item.find('.description').text(itemData.description)
	if(itemData.completed){
		item.addClass('completed')
	}
	list.append(item)
}

var getList = $.ajax({
	type: 'GET',
	url: "https://listalous.herokuapp.com/lists/RobbyK/"
});

getList.done(getListSuccess);

function getListSuccess(dataFromServer){
var itemsData = dataFromServer.items

	itemsData.forEach(function(itemData) {
		addItemToPage(itemData)
	});
}

var newItem = $('#add-form');

newItem.on('submit', newItemSubmit);

function newItemSubmit(event) {
	event.preventDefault();
	var itemDescription = event.target.itemDescription.value;

	var saveItem = $.ajax({
		type: 'POST',
		url: "https://listalous.herokuapp.com/lists/RobbyK/items",
		data: {description: itemDescription, completed: false}
	})

	saveItem.done(function(newItemData){
	addItemToPage(newItemData)
	});
	$('#create').val('').blur();
}

$('#list').on('click', '.delete-button', function(event) {
	var item = $(event.target).parent()
	var itemId = item.attr('data-id')
	$.ajax({
		type : 'DELETE',
		url: "https://listalous.herokuapp.com/lists/RobbyK/items/" + itemId
	})
	list.empty();
	getList.done(getListSuccess);
})

$('#list').on('click', '.complete-button', function(event) {
  var item = $(event.target).parent()
  var isItemCompleted = item.hasClass('completed')
  var itemId = item.attr('data-id')

  var updateRequest = $.ajax({
    type: 'PUT',
    url: "https://listalous.herokuapp.com/lists/RobbyK/items/" + itemId,
    data: { completed: !isItemCompleted }
  })

  updateRequest.done(function(itemData) {
    if (itemData.completed) {
      item.addClass('completed')
    } else {
      item.removeClass('completed')
    }
  })
})
