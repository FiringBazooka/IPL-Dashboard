import { Link } from "react-router-dom";


const MatchAdditionalDetails = ({match,teamName}) => {
	
	const otherTeamName = match.team1===teamName ? match.team2 : match.team1;
	const otherTeamRoute = `/team/${otherTeamName}`;
	return (
		<div> 
			
				<b><p> vs
					 <Link to={otherTeamRoute}> {otherTeamName} </Link>
				</p></b>
			

		  <p>{match.winner} won by {match.resultMargin} {match.result}</p>
		  
		</div>
	)
}

export default MatchAdditionalDetails;