
//function that makes getAll request, is called upon loading films.html
function showAllFilms(){
    fetch('/api/films/getAll')
    .then(res => {
        res.json().then(body => {
                data = JSON.stringify(body);
                console.log(data);
                displayAllInBody(body); //display body of json response in the table of films
           });
        })
    .catch(err =>{
        console.log(err);
        document.body.innerHTML= `<p>Error: ${err.message}</p>`;
    })
}



function displayAllInBody(res){
    let tbody = document.getElementById("film-table-body")
    tbody.innerHTML = "";
    //loops through each object in the json response and makes a table row out of it
    res.forEach(film => {
        tbody.appendChild(populateRow(film));
    });
}

function displayBody(body){
    let old_tbody = document.getElementById("film-table-body")
    let new_tbody = document.createElement("tbody");
    new_tbody;
    old_tbody.parentNode.replaceChild(new_tbody, old_tbody);
    new_tbody.id = "film-table-body";
    populateRow(film);
}

//populates a row in the film table with attributes of a film json object
function populateRow(film){
    let table = document.getElementById("film-table-body");
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

//called when clicking the add film button after providing input fields
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

function searchFilm(){
    let name = document.getElementById("searchname").value;
    fetch(`/api/films/search/${name}`, {method: "GET"})
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

function openSearchForm() {
    document.getElementById("searchFilmForm").style.display = "block";
}
  
function closeSearchForm() {
    document.getElementById("searchFilmForm").style.display = "none";
}

function filterFilm(){
    let director = document.getElementById("filterdirector").value;
    let year = document.getElementById("filteryear").value;
    let genre = document.getElementById("filtergenre").value
    fetch(`/api/films/searchBy?director=${director}&genre=${genre}&year=${year}`, {method: "GET"})
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

function openFilterForm() {
    document.getElementById("filterFilmForm").style.display = "block";
}
  
function closeFilterForm() {
    document.getElementById("filterFilmForm").style.display = "none";
}

async function getFilm(id){
    const res = await fetch(`/api/films/getFilm/${id}`);

    return await res.json();
    
}

async function addFilmToList(){
    let id = document.getElementById("listfilmId").value;
    let film = await getFilm(id);
    console.log(film);
    film.filmId=null;
    let status = document.getElementById("listfilmstatus").value;
    let progress;
    if(status === "Complete"){
        progress = 1;
    }
    else{
        progress = 0;
    }
    fetch(`/api/list/addFilm?status=${status}&progress=${progress}`,
        {
            method: "POST",
            body: JSON.stringify(film),
            headers: {
                "Content-Type": "application/json"
            }
        }
    ).then(res => {
        res.json().then(body => {
                data = JSON.stringify(body);
                console.log(data);
           });
        })
    .catch(err =>{
        console.log(err);
        document.body.innerHTML= `<p>Error: ${err.message}</p>`;
    })
}

function openAddListForm() {
    document.getElementById("addListForm").style.display = "block";
}
  
function closeAddListForm() {
    document.getElementById("addListForm").style.display = "none";
}