function showAllTVShows(){
    fetch('/api/tvshows/getAll')
    .then(res => {
        res.json().then(body => {
                data = JSON.stringify(body);
                console.log(data);
                displayAllInBody(body); //display body of json response in the table of tv shows
           });
        })
    .catch(err =>{
        console.log(err);
        document.body.innerHTML= `<p>Error: ${err.message}</p>`;
    })
}

function displayAllInBody(res){
    let tbody = document.getElementById("show-table-body")
    tbody.innerHTML = "";
    //loops through each object in the json response and makes a table row out of it
    res.forEach(show => {
        tbody.appendChild(populateRow(show));
    });
}

function populateRow(show){
    let table = document.getElementById("show-table-body");
    let row = document.createElement("tr");

    let id = document.createElement("th");
    id.scope = "row";
    id.innerHTML = `${show.showId}`;
    row.appendChild(id);

    let name = document.createElement("td");
    name.innerHTML = `${show.showName}`;
    row.appendChild(name);
    
    let yearBegan = document.createElement("td");
    yearBegan.innerHTML = `${show.yearBegan}`;
    row.appendChild(yearBegan);

    let yearEnded = document.createElement("td");
    yearEnded.innerHTML = `${show.yearEnded}`;
    row.appendChild(yearEnded);

    let genre = document.createElement("td");
    genre.innerHTML = `${show.genre}`;
    row.appendChild(genre);

    let episodes = document.createElement("td");
    episodes.innerHTML = `${show.episodes}`;
    row.appendChild(episodes);

    let seasons = document.createElement("td");
    seasons.innerHTML = `${show.seasons}`;
    row.appendChild(seasons);

    return row;
}

//called when clicking the add tv show button after providing input fields
function create(){
    let name = document.getElementById("name").value;
    let yearBegan = document.getElementById("yearBegan").value;
    let yearEnded = document.getElementById("yearEnded").value;
    let genre = document.getElementById("genre").value;
    let episodes = document.getElementById("episodes").value;
    let seasons = document.getElementById("seasons").value;
    console.log(JSON.stringify({
        "showName": name,
        "yearBegan": yearBegan,
        "yearEnded": yearEnded,
        "genre": genre,
        "episodes": episodes,
        "seasons": seasons
    }))
    fetch("/api/tvshows/add", {
            method: "POST",
            body: JSON.stringify({
                "showName": name,
                "yearBegan": yearBegan,
                "yearEnded": yearEnded,
                "genre": genre,
                "episodes": episodes,
                "seasons": seasons
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
    document.getElementById("addShowForm").style.display = "block";
}
  
function closeAddForm() {
    document.getElementById("addShowForm").style.display = "none";
}

function updateShow(){
    let id = document.getElementById("upshowId").value;
    let name = document.getElementById("upname").value;
    let yearBegan = document.getElementById("upyearBegan").value;
    let yearEnded = document.getElementById("upyearEnded").value;
    let genre = document.getElementById("upgenre").value;
    let episodes = document.getElementById("upepisodes").value;
    let seasons = document.getElementById("upseasons").value;
    console.log(JSON.stringify({
        "showId": id,
        "showName": name,
        "yearBegan": yearBegan,
        "yearEnded": yearEnded,
        "genre": genre,
        "episodes": episodes,
        "seasons": seasons
    }))
    fetch(`/api/tvshows/updateShow/${id}`, {
            method: "PUT",
            body: JSON.stringify({
                "showName": name,
                "yearBegan": yearBegan,
                "yearEnded": yearEnded,
                "genre": genre,
                "episodes": episodes,
                "seasons": seasons
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

function openUpdateForm(){
    document.getElementById("updateShowForm").style.display = "block";
}

function closeUpdateForm(){
    document.getElementById("updateShowForm").style.display = "none";
}

function deleteShow(){
    let id = document.getElementById("delshowId").value;
    
    fetch(`/api/tvshows/delete/${id}`, {
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

function openDeleteForm(){
    document.getElementById("deleteShowForm").style.display = "block";
}

function closeDeleteForm(){
    document.getElementById("deleteShowForm").style.display = "none";
}

function searchShow(){
    let name = document.getElementById("searchname").value;
    fetch(`/api/tvshows/search/${name}`,
        {
            method: "GET",
        }
    )
    .then(res => {
        res.json().then(body => {
                data = JSON.stringify(body);
                console.log(data);
                displayAllInBody(body); //display body of json response in the table of tv shows
           });
        })
    .catch(err =>{
        console.log(err);
        document.body.innerHTML= `<p>Error: ${err.message}</p>`;
    })
}

function openSearchForm(){
    document.getElementById("searchShowForm").style.display = "block";
}

function closeSearchForm(){
    document.getElementById("searchShowForm").style.display = "none";
}

function filterShows(){
    let episodes = document.getElementById("filterepisodes").value;
    let seasons = document.getElementById("filterseasons").value;
    let genre = document.getElementById("filtergenre").value;
    let year = document.getElementById("filteryear").value;
    fetch(`/api/tvshows/searchBy?episodes=${episodes}&seasons=${seasons}&genre=${genre}&year=${year}`, {method: "GET"})
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

function openFilterForm(){
    document.getElementById("filterShowForm").style.display = "block";
}

function closeFilterForm(){
    document.getElementById("filterShowForm").style.display = "none";
}