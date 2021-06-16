
const MatchDetails = ({team}) => {

	if(!team.list[0])	return null;
	return (
		<div>
		  <h5>Total matches Played : {team.totalMatchesPlayed}</h5>
		  <h5>Total matches Won : {team.totalWon}</h5>
		  <h4>Last Match was against {team.list[0].team2} at {team.list[0].venue} on {team.list[0].date} </h4>
		  <h4>where {team.list[0].winner} won by {team.list[0].resultMargin} {team.list[0].result}</h4>
		  <h4>Man of the Match : {team.list[0].playerOfMatch}</h4>
		 
		</div>
	)
}

export default MatchDetails;
