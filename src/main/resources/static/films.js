function showAllFilms(){
    fetch('/api/films/getAll')
    .then(res => {
        res.json().then(body => {
                displayBody(body);
           });
        })
    .catch(err =>{
        console.log(err);
        document.body.innerHTML= `<p>Error: ${err.message}</p>`;
    })
}

function displayBody(body){
    let old_tbody = document.getElementById("film-table-body")
    let new_tbody = document.createElement("tbody");
    new_tbody;
    old_tbody.parentNode.replaceChild(new_tbody, old_tbody);
    new_tbody.id = "film-table-body";
    body.forEach(film => {
        populateRow(film);
    });
}

function populateRow(film){
    let table = document.getElementById("film-table-body");
    let row = document.createElement("tr");
    let id = document.createElement("th");
    let gap = document.createElement("gap");
    gap.innerHTML = " ";
    id.scope = "row";
    id.innerHTML = `${film.filmId}`;
    table.appendChild(id);
    //row.appendChild(gap);
    let name = document.createElement("td");
    name.innerHTML = `${film.filmName}`;
    row.appendChild(name);
    let director = document.createElement("td");
    director.innerHTML = `${film.director}`;
    row.appendChild(director);
    let year = document.createElement("td");
    year.innerHTML = `${film.yearRelease}`;
    row.appendChild(year);
    let genre = document.createElement("td");
    genre.innerHTML = `${film.genre}`;
    row.appendChild(genre);
    let runtime = document.createElement("td");
    runtime.innerHTML = `${film.runtime}`;
    row.appendChild(runtime);
}