import { setClsPrefixHOC } from '@/utils/setClsPrefixHOC';
import React, { HTMLAttributes, memo } from 'react';
import Prefix from '../constants';

import './index.less';

const setClsPrefix = setClsPrefixHOC(Prefix.chartLoading);

interface ChartLoadingBaseProps {
  className?: string;
  loading?: boolean;
}

type ChartLoadingProps = ChartLoadingBaseProps & HTMLAttributes<HTMLElement>;

const ChartLoading = memo<ChartLoadingProps>((props) => {
  const { loading, children, className, ...restProps } = props;

  return (
    <div {...restProps} className={setClsPrefix()}>
      {loading && (
        <div className={setClsPrefix('container')}>
          <div className={setClsPrefix('container-loading')}>
            <div className={setClsPrefix('double-bounce1')}></div>
            <div className={setClsPrefix('double-bounce2')}></div>
          </div>
        </div>
      )}
      {!loading && <div className={setClsPrefix('children')}>{children}</div>}
    </div>
  );
});

export default ChartLoading;
