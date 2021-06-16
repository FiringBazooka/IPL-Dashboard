import {useEffect, useState} from 'react'
import MatchDetails from './MatchDetails';
import MatchAdditionalDetails from './MatchAdditionalDetails';
import { useParams } from 'react-router';


const TeamPage = () => {

const[team, setTeam]=useState({list:[]});

// get the path parameter from the URL - 
// structure of the URI defined in App.js
const {teamName}=useParams();

    /*  useEffect takes two arguments - a Function and a parameter
        Function cannot be made async - hence one more function inside it is created to make it async
        parameter if not given - makes unlimited calls as the state changes
        parameter if marked as empty [] - makes a single call on page load
        [teamName] - whenever the teamName changes (when clicked on the Link in MatchAdditionalDetails component), useEffect is called
    */ 
    useEffect(
        () => {
            const serviceCall = async () => {

                // use the tick ` instead of single quote when something is dynamic inside a String.
                const response = await fetch(`http://localhost:8080/team/${teamName}`);
                const data = await response.json();
                setTeam(data);
                console.log(data)
            };
            serviceCall();
        }, [teamName]
    );

    if(!team || !teamName){
        return <h1>Team Not Found</h1>
    }
    return (

        <div>
            <h2>{team.teamName}</h2>

            <MatchDetails team={team} />
            <h3>Other Matches</h3>
            {team.list.slice(1).map(match =>  <MatchAdditionalDetails match={match} teamName={teamName} / >)}

        </div>
    )
}

export default TeamPage;