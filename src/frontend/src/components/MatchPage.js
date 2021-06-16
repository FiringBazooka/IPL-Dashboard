import { useEffect, useState } from "react";
import { useParams } from "react-router";
import MatchDetails from "./MatchDetails";

const MatchPage = () => {

    const{teamName, year}=useParams();
    const[matches,setMatches] = useState([]);

    useEffect(
        () => {
            const serviceCall = async () => {
                const response = await fetch(`http://localhost:8080/team/${teamName}/matches?year=${year}`)
                const data = await response.json();
                console.log(data);
                setMatches(data);
            };
            serviceCall();
        },[]
    )

    return (
        <div>
            <h1>Match Page</h1>
            {
                matches.map(match => <MatchDetails match={match}/>)
            }
        
        </div>
    )
}

export default MatchPage;