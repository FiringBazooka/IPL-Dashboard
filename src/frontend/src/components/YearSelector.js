import {React} from 'react'
import { Link } from 'react-router-dom';
import './YearSelector.scss'

const YearSelector = ({yearSelected, teamName}) =>{
    
    const startYear = 2008;
    const endYear = 2020;
    const years=[]
    
    for(let i=startYear; i <=endYear; i++){
        years.push(i);
    }
    
    
    return (
        years.map(year=>
           
        (
            <div className={year==yearSelected ? 'YearSelector highlight-year':'YearSelector'}>
                <h3>
                  <Link className='year-link' to={`/team/${teamName}/matches/${year}`}>{year}</Link>  
                </h3>
            </div>
        )
    
       )
    )


}

export default YearSelector;