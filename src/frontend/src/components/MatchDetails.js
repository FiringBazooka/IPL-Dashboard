

const MatchDetails = ({team,match}) => {

	if(!match)	return null;

	

	return (
		<div>
		
		  <h4>vs {match.team2} at {match.venue} on {match.date} </h4>
		  <h4>where {match.winner} won by {match.resultMargin} {match.result}</h4>
		  <h4>Man of the Match : {match.playerOfMatch}</h4>
		 
		</div>
	)
}

export default MatchDetails;
