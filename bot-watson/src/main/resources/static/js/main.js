'use strict';

document.querySelector('#welcomeForm').addEventListener('submit', connect, true)
document.querySelector('#dialogueForm').addEventListener('submit', sendMessage, true)

let stompClient = null;
let name = null;

function connect(event) {
	name = document.querySelector('#name').value.trim();

	if (name) {
		document.querySelector('#welcome-page').classList.add('hidden');
		document.querySelector('#dialogue-page').classList.remove('hidden');

		var socket = new SockJS('/websocketApp');
		stompClient = Stomp.over(socket);

		stompClient.connect({}, connectionSuccess);
	}
	event.preventDefault();
}

function connectionSuccess() {
	stompClient.subscribe('/topic/public', onMessageReceived);

	stompClient.send("/app/chat.addUser", {}, JSON.stringify({
		sender : name,
		type : 'newUser'
	}))

    stompClient.send("/app/chat.addUser", {}, JSON.stringify({
    		sender : 'Optimus',
    		type : 'newUser'
    	}))
    stompClient.send("/app/chat.welcomeFromBot", {}, JSON.stringify({
            sender : 'Optimus',
            type : 'initiate-chat'
        }));
    		document.querySelector('#chatMessage').value = '';

}

function sendMessage(event) {
	let messageContent = document.querySelector('#chatMessage').value.trim();

	if (messageContent && stompClient) {
		let chatMessage = {
			sender : name,
			content : document.querySelector('#chatMessage').value,
			type : 'CHAT'
		};



    stompClient.send("/app/chat.sendMessage", {}, JSON
        				.stringify(chatMessage));
    document.querySelector('#chatMessage').value = '';

    stompClient.send("/app/chat.botMessage", {}, JSON
                		.stringify(chatMessage));
    document.querySelector('#chatMessage').value = '';

	}

	event.preventDefault();
}





function onMessageReceived(payload) {
	let message = JSON.parse(payload.body);

	let messageElement = document.createElement('li');

	if (message.type === 'newUser') {
		messageElement.classList.add('event-data');
		message.content = message.sender + ' has joined the chat';
	} else if (message.type === 'Leave') {
		messageElement.classList.add('event-data');
		message.content = message.sender + ' has left the chat';
	} else {
		messageElement.classList.add('message-data');

		let element = document.createElement('i');
		let text = document.createTextNode(message.sender[0]);
		element.appendChild(text);

		messageElement.appendChild(element);

		let usernameElement = document.createElement('span');
		let usernameText = document.createTextNode(message.sender);
		usernameElement.appendChild(usernameText);
		messageElement.appendChild(usernameElement);
	}

	let textElement = document.createElement('p');
	let messageText = document.createTextNode(message.content);
	textElement.appendChild(messageText);

	messageElement.appendChild(textElement);

	document.querySelector('#messageList').appendChild(messageElement);
	document.querySelector('#messageList').scrollTop = document
			.querySelector('#messageList').scrollHeight;

}