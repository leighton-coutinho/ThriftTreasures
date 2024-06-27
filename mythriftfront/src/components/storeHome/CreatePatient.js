import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';

import { useNavigate } from 'react-router-dom';

const CreatePatient = (props) => {
  // Define the state with useState hook
  const navigate = useNavigate();
  const [patient, setPatient] = useState({
    name: '',
    id: '',
    medication: '',
    times: '',
    number: '',
    medTimes: [],
  });

  const onChange = (e) => {
    setPatient({ ...patient, [e.target.name]: e.target.value });
    converttimes(patient.times);
  };

  // build function to convert no of times per day into list of times
  function converttimes(timesperday) {
    let starttime = 10;
    let stoptime = 22;
    let timelist = [];
    let window = stoptime - starttime;
    let interval = window / (timesperday-1);
    let count = 0;
    while (count < timesperday) {
      timelist.push((count) * interval + starttime)
      count += 1
    }
    console.log(timelist);
    setPatient(prevPatient => ({ ...prevPatient, medTimes: timelist }));
  };

  const onSubmit = (e) => {
    //console.log('OUTSIDE');
    e.preventDefault();
    // add fields here before posting to database (i.e convert into list of times, index, key  etc)
    //converttimes(patient.times);
    axios.post('http://localhost:8082/api/patients', patient).then((res) => {
        setPatient({
          name: '',
          id: '',
          medication: '',
          times: '',
          number: '',
        });

        // Push to /
        navigate('/product');
      })
      .catch((err) => {
        console.log('Error in CreatePatient!');
      });
  };

  return (
    <div className='CreatePatient'>
      <div className='container'>
        <div className='row'>
          <div className='col-md-8 m-auto'>
            <br />
            <Link to='/product' className='btn btn-outline-warning float-left'>
              Back to Home
            </Link>
          </div>
          <div className='col-md-8 m-auto'>
            <h1 className='display-4 text-center'>Register a Patient</h1>
            <p className='lead text-center'>Please fill in all details ( if more then one medication, you may submit several times )</p>

            <form noValidate onSubmit={onSubmit}>
              <div className='form-group'>
                <input
                  type='text'
                  placeholder=' First Name of the Patient'
                  name='name'
                  className='form-control'
                  value={patient.name}
                  onChange={onChange}
                />
              </div>

              <div className='form-group'>
                <input
                  type='text'
                  placeholder='Patient ID'
                  name='id'
                  className='form-control'
                  value={patient.id}
                  onChange={onChange}
                />
              </div>

              <div className='form-group'>
                <input
                  type='text'
                  placeholder='Medication Name'
                  name='medication'
                  className='form-control'
                  value={patient.medication}
                  onChange={onChange}
                />
              </div>

              <div className='form-group'>
                <input
                  type='text'
                  placeholder='Amount of times per day'
                  name='times'
                  className='form-control'
                  value={patient.times}
                  onChange={onChange}
                />
              </div>

              <div className='form-group'>
                <input
                  type='text'
                  placeholder='Phone Number ( e.g 438209102 )'
                  name='number'
                  className='form-control'
                  value={patient.number}
                  onChange={onChange}
                />
              </div>

              <input
                type='submit'
                className='btn btn-outline-warning btn-block mt-4'
              />
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CreatePatient;