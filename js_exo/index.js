// A partir du site JSONPlaceholder :https://jsonplaceholder.typicode.com/ :

// Créer une application Javascript (client ou serveur) qui effectuera les actions suivantes avec
// Fetch et Axios :

// • Récupérer tous les todos
// • Récupérer le todo avec l’id 10
// • Créer un todo
// • mettre à jour le todo 10
// • mettre à jour le title du todo 10

// Afficher les réponses obtenues avec console.log



const apiUrl = "https://jsonplaceholder.typicode.com";
const postDATA = 
  {
    userId: 1,    
    title: "Manger des fruits",
    completed: false
  };
const axios = require('axios');

testFetch();
testAxios()

// api request using fetch method
async function testFetch () {
  
  // get all todos
  fetch(apiUrl+"/todos").then(response => {
    if (!response.ok) {
      throw Errors(response.statusText);
    }
    return response.json();
  })
  .then(data => console.log(data))
  .catch(error => console.log(error));
  
  // get one todo
  fetch(apiUrl+"/todos/10").then(response => {
    if (!response.ok) {
      throw Errors(response.statusText);
    }
    return response.json();
  })
  .then(data => console.log(data))
  .catch(error => console.log(error));
  
  // create one todo
  await fetch(apiUrl+"/todos", {
    method: 'POST',
    body: JSON.stringify(postDATA),
    header: { 'Content-type': 'application/json; charset=UTF-8',},
  }).then(response => response.json())
  .then(data => console.log('create success: ', data))
  .catch(error => console.log(error));
  
  // update one todo
  await fetch(apiUrl+"/todos/10", {
    method: 'PUT',
    header: { 'Content-type': 'application/json; charset=UTF-8',},
    body: JSON.stringify(postDATA),
  }).then(response => response.json())
  .then(data => console.log('update put success: ', data))
  .catch(error => console.log(error));

  // update one todo
  await fetch(apiUrl+"/todos/10", {
    method: 'PATCH',
    body: JSON.stringify(postDATA),
    header: { 'Content-type': 'application/json; charset=UTF-8',},
  }).then(response => response.json())
  .then(data => console.log('update patch success: ', data))
  .catch(error => console.log(error));

  // delete one todo
  await fetch(apiUrl+"/todos/10", {
    method: 'DELETE',
  }).then(response => response.json())
  .then(data => console.log('delete success: ', data))
  .catch(error => console.log(error));
}

// api request using axios method
async function testAxios() {
  // get all todos
  await axios.get(apiUrl+"/todos")
  .then(response => console.log(response))
  .catch(error => console.log(error.message));

  //  get one todo
  await axios.get(apiUrl+"/todos/10")
  .then(response => console.log(response.data))
  .catch(error => console.log(error.message));

  // create todo
  await axios({
    method: "POST",
    url: apiUrl+'/todos',
    data: postDATA,
  })
  .then(response => console.log('axios create success: ', response.data))
  .catch(error => console.log('error: ', error));

  // update todo
  await axios({
    method: "put",
    url: apiUrl+'/todos/10',
    data: postDATA,
  })
  .then(response => console.log('axios update put success: ', response.data))
  .catch(error => console.log('error: ', error));

   // delete todo
   await axios({
    method: "delete",
    url: apiUrl+'/todos/10',
  })
  .then(response => console.log('axios delete success: ', response.data))
  .catch(error => console.log('error: ', error));
}