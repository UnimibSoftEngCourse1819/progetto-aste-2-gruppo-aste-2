function append(text) {
	if(text !== "") {
		var node = document.createElement("DIV");
		var textnode = document.createTextNode(text);
		var del = document.createElement("DIV");
		var text = document.createElement("DIV");
		
		node.classList.add("category");
		text.classList.add("cat-text");
		text.appendChild(textnode);
		node.appendChild(text);
		
		del.classList.add("remove");
		del.innerHTML = "&times;";
		del.setAttribute("onclick", "remove(this)");
		node.appendChild(del);
		document.getElementById("category-container").appendChild(node);
	}
}

function remove(element) {
	element.parentNode.remove();
}