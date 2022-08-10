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