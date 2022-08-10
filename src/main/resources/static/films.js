function showAllFilms(){
    fetch('/api/films/getAll')
    .then(res => {
        res.json().then(body => {
                data = JSON.stringify(body);
                console.log(data);
                displayAllInBody(body);
           });
        })
    .catch(err =>{
        console.log(err);
        document.body.innerHTML= `<p>Error: ${err.message}</p>`;
    })
}

function showFilm(id){
    fetch(`/api/films/getFilm/${id}`)
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

function displayAllInBody(res){
    let old_tbody = document.getElementById("film-table-body")
    let new_tbody = document.createElement("tbody");
    new_tbody;
    old_tbody.parentNode.replaceChild(new_tbody, old_tbody);
    new_tbody.setAttribute("id", "filmt-table-body");
    res.forEach(film => {
        new_tbody.appendChild(populateRow(film));
    });
}

function disaplyBody(body){
    let old_tbody = document.getElementById("film-table-body")
    let new_tbody = document.createElement("tbody");
    new_tbody;
    old_tbody.parentNode.replaceChild(new_tbody, old_tbody);
    new_tbody.id = "film-table-body";
    populateRow(film);
}

function populateRow(film){
    let table = document.getElementById("film-table-body");
    let row = document.createElement("tr");
    let id = document.createElement("th");
    // let gap = document.createElement("gap");
    // gap.innerHTML = " ";
    id.scope = "row";
    id.innerHTML = `${film.filmId}`;
    row.appendChild(id);
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
    return row;
}

function create(){
    let name = document.getElementById("name").value;
    let director = document.getElementById("director").value;
    let year = document.getElementById("year").value;
    let genre = document.getElementById("genre").value;
    let runtime = document.getElementById("runtime").value;
    console.log(JSON.stringify({
        "filmName": name,
        "director": director,
        "yearRelease": year,
        "genre": genre,
        "runtime": runtime
    }))
    fetch("/api/films/add?", {
            method: "POST",
            body: JSON.stringify({
                "filmName": name,
                "director": director,
                "yearRelease": year,
                "genre": genre,
                "runtime": runtime
            }),
            headers: {
                "Content-Type": "application/json"
            }
        } 
    ).then(res => {
        res.json().then(body => {
                //data = JSON.stringify(body);
                console.log(body);
           });
        })
    .catch(err =>{
        console.log(err);
        document.body.innerHTML= `<p>Error: ${err.message}</p>`;
    })

}

function openAddForm() {
    document.getElementById("addFilmForm").style.display = "block";
}
  
function closeAddForm() {
    document.getElementById("addFilmForm").style.display = "none";
}

function update(){
    let id = document.getElementById("upfilmId").value;
    let name = document.getElementById("upname").value;
    let director = document.getElementById("updirector").value;
    let year = document.getElementById("upyear").value;
    let genre = document.getElementById("upgenre").value;
    let runtime = document.getElementById("upruntime").value;
    console.log(JSON.stringify({
        "filmName": name,
        "director": director,
        "yearRelease": year,
        "genre": genre,
        "runtime": runtime
    }))
    fetch(`/api/films/updateFilm/${id}`, {
            method: "PUT",
            body: JSON.stringify({
                "filmName": name,
                "director": director,
                "yearRelease": year,
                "genre": genre,
                "runtime": runtime
            }),
            headers: {
                "Content-Type": "application/json"
            }
        } 
    ).then(res => {
        res.json().then(body => {
                //data = JSON.stringify(body);
                console.log(body);
           });
        })
    .catch(err =>{
        console.log(err);
        document.body.innerHTML= `<p>Error: ${err.message}</p>`;
    })
}

function openUpdateForm() {
    document.getElementById("updateFilmForm").style.display = "block";
}
  
function closeUpdateForm() {
    document.getElementById("updateFilmForm").style.display = "none";
}

function deleteFilm(){
    let id = document.getElementById("delfilmId").value;
    
    fetch(`/api/films/delete/${id}`, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json"
            }
        } 
    ).then(res => {
        res.json().then(body => {
                //data = JSON.stringify(body);
                console.log(body);
           });
        })
    .catch(err =>{
        console.log(err);
        document.body.innerHTML= `<p>Error: ${err.message}</p>`;
    })
}

function openDeleteForm() {
    document.getElementById("deleteFilmForm").style.display = "block";
}
  
function closeDeleteForm() {
    document.getElementById("deleteFilmForm").style.display = "none";
}