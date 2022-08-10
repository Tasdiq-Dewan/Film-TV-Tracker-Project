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
    const old_tbody = document.getElementById("film-table-body")
    const new_tbody = document.createElement("film-table-body");
    old_tbody.parentNode.replaceChild(new_tbody, old_tbody);
    body.forEach(film => {
        new_tbody.appendChild(populateRow(film));
    });
}

function populateRow(film){
    let row = document.createElement("tr");
    let id = document.createElement("th");
    id.scope = "row";
    id.innerHTML = `${film.filmId}`;
    row.appendChild(id);
    let name = document.createElement("td");
    name.innerHTML = `${film.filmName}`;
    row.appendChild(name);
    let director = document.createElement("td");
    director.innerHTML = `${film.director}`;
    row.appendChild(director);
    let year = createElement("td");
    year.innerHTML = `${film.yearRelase}`;
    row.appendChild(year);
    let genre = createElement("td");
    genre.innerHTML = `${film.genre}`;
    row.appendChild(genre);
    let runtime = createElement("td");
    runtime.innerHTML = `${film.runtime}`;
    row.appendChild(runtime);
    return row;
}