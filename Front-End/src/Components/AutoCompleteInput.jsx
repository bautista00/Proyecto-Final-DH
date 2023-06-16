import { AutoComplete } from 'antd';
import { useState } from 'react';

const AutoCompleteInput = () => {

    const options = [
  {
    value: 'Futbol',
  },
  {
    value: 'Basket',
  },
  {
    value: 'Padel',
  },
  {
    value: 'Tenis',
  },
];
  return (
    <AutoComplete
    style={{
      width: '10rem',
    }}
    options={options}
    placeholder="¿Qué buscas?"
    filterOption={(inputValue, option) =>
      option.value.toUpperCase().indexOf(inputValue.toUpperCase()) !== -1
    }
  />
  )
}

export default AutoCompleteInput