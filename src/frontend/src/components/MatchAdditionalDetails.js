import { Link } from "react-router-dom";
import './MatchAdditionalDetails.scss'

const MatchAdditionalDetails = ({match,teamName}) => {
	
	const otherTeamName = match.team1===teamName ? match.team2 : match.team1;
	const otherTeamRoute = `/team/${otherTeamName}`;
	const isTeamWon = teamName === match.winner;

	return (
		<div className={isTeamWon ? 'matchAdditionalDetails winning-team': 'matchAdditionalDetails losing-team'}> 
			
				<b><p> vs
					 <Link className='other-team-link' to={otherTeamRoute}> {otherTeamName} </Link>
				</p></b>
			

		  <p>{match.winner} won by {match.resultMargin} {match.result}</p>
		  <p>{match.date}</p>
		  

		</div>
	)
}

export default MatchAdditionalDetails;