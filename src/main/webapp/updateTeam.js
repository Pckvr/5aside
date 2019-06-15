function populateTeamDetails(teamId) {
    dataRequest("GET", "/team/" + teamId).then((request) => {
        let teamDetails = JSON.parse(request.responseText);
        document.getElementById('teamNameUpdate').value = teamDetails.name;
        document.getElementById('homeColourUpdate').value = teamDetails.homeColour;
        document.getElementById('awayColourUpdate').value = teamDetails.awayColour;
    })
}

function updateTeam(teamId) {
    dataRequest("GET", "/team/" + teamId).then((request) => {
        let team = JSON.parse(request.responseText)

        if (document.getElementById("teamNameUpdate").value != "") {
            team.name = document.getElementById("teamNameUpdate").value;
        }
        if (document.getElementById("homeColourUpdate").value != "") {
            team.homeColour = document.getElementById("homeColourUpdate").value;
        }
        if (document.getElementById("awayColourUpdate").value != "") {
            team.awayColour = document.getElementById("awayColourUpdate").value;
        }

        let teamJSON = JSON.stringify(team);
        console.log(teamJSON);
        dataRequest("PUT", "/team/" + teamId, teamJSON).then((request) => {
            console.log(request.responseText);
            window.location.href = '/5aside-1.0/team.html';
        }
        )
    })
}
