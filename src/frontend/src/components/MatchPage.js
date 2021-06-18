import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { Link } from "react-router-dom";
import MatchDetails from "./MatchDetails";
import './MatchPage.scss'
import YearSelector from "./YearSelector";



const MatchPage = () => {

    const{teamName, year}=useParams();
    const[matches,setMatches] = useState([]);

    useEffect(
        () => {
            const serviceCall = async () => {
                const response = await fetch(`http://localhost:8080/team/${teamName}/matches?year=${year}`)
                const data = await response.json();
                setMatches(data);
            };
            serviceCall();
        },[teamName,year]
    )

    const getMatchDetails = ()=>{
        if(!matches || !matches.length) return <h1>Not Featured in this season.</h1>;
        return matches.map(match => <MatchDetails match={match} team={teamName}/>);
    };

    return (

        
        <div className='MatchPage'>
            <div className='year-list'> <h4>select year </h4>
            <YearSelector yearSelected={year} teamName={teamName}/>
          </div>
            
            <div>
            <h1 className='teamName-header-h1'> <Link className='teamName-header' to={`/team/${teamName}`}>{teamName}</Link></h1>
            {getMatchDetails()}
            </div>
        
        </div>
    )
}

export default MatchPage;