function send() {
	var title = document.getElementById("titolo").value;
	var description = document.getElementById("descrip").value;
	var mode = document.getElementById("mod").value;
	var categories = [];
	var elements = document.getElementsByClassName("cat-text");
	var refund = document.querySelector('input[name=refund]:checked').value;
	
	for(var i = 0; i < elements.length; ++i) {
		categories.push(elements[i].innerHTML);
	}
	
	$.ajax({
		url: "auctionCreation",
		type: "POST",
		data: {
			"auctionTitle" : title,
			"auctionDescription" : description,
			"mod" : mode,
			"categories" : categories,
			"refund": refund
		},
		success: function(data) {
			console.log(data);
		}
	});
}