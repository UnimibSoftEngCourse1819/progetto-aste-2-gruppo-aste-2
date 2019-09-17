function append(text) {
	if(text !== "") {
		var node = document.createElement("DIV");
		var textnode = document.createTextNode(text);
		var del = document.createElement("DIV");
		
		node.classList.add("category");
		node.appendChild(textnode);
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