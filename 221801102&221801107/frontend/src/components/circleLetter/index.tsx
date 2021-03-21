import React from 'react';
import { setClsPrefixHOC } from '@/utils/setClsPrefixHOC';
import Prefix from '../constants';
import './index.less';

const setClsPrefix = setClsPrefixHOC(Prefix.circleLetter);

interface CircleLetterProps {
  letter: string;
}

const CircleLetter = (props: CircleLetterProps) => {
  const { letter } = props;
  return (
    <div className={setClsPrefix()}>
      <div className={setClsPrefix('circle')}> {letter.slice(0, 1)}</div>
      <div title={letter}>
        {letter.length > 7 ? letter.slice(0, 6) + '...' : letter}
      </div>
    </div>
  );
};

export default CircleLetter;
