import './MatchDetails.scss'

const MatchDetails = ({team,match}) => {


	if(!match)	return null;
	const isTeamWon = team === match.winner;
	const otherTeamName = match.team1===team ? match.team2 : match.team1;

	
	return (
		<div className={isTeamWon ? 'MatchDetails winning-team': 'MatchDetails losing-team'}>
		<div>
		  <h4>vs {otherTeamName} at {match.venue} on {match.date} </h4>
		  	<h4>{match.winner} won by {match.resultMargin} {match.result}</h4>
		  </div>

		  <div className='column-2'>
			<h4>{match.tossWinner} won the toss and chose to  {match.tossDecision} first.</h4>	  
		  	<h4>Man of the Match : {match.playerOfMatch}</h4>
		 	 <h4>Umpires: {match.umpire1},{match.umpire2}</h4>
		  </div>
		 
		</div>
	)
}

export default MatchDetails;
