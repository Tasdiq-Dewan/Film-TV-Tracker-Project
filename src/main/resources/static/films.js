function showAllFilms(){
    fetch('/api/films/getAll')
    .then(res => {
        res.json().then(body => {
            displayBody(JSON.stringify(body));
        })
    })
    .catch(err =>{
        console.log(err);
        document.body.innerHTML= `<p>Error: ${err.message}</p>`;
    })
}

function displayBody(body){
    const old_tbody = document.getElementById("film-table-body")
    const new_tbody = document.createElement("film-table-body");
    old_tbody.parentNode.replaceChild(new_tbody, old_tbody);
    for(var film in body){
        new_tbody.appendChild(populateRow(film));
    }
}

function populateRow(film){
    let row = document.createElement("tr");
    let id = document.createElement("th");
    id.scope = "row";
    id.innerHTML = film['filmId'];
    row.appendChild(id);
    
    return row;
}