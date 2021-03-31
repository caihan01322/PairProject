/**
 * 遍历 localStorage
 * @param localStorage
 */
const loopLocalStorage = (localStorage: Storage) => {
  const len = localStorage.length;
  const arr = [];
  for (let i = 0; i < len; i++) {
    const key = localStorage.key(i) as string;
    if (key === '__isLogin__' || key === '__username__' || key === '__avatar__') {
      continue;
    }
    const value = parseInt(localStorage.getItem(key) as string);

    arr.push({
      key,
      value,
    });
  }
  return arr;
};

/**
 * 排序
 * @param localStorage localStorage
 */
const sortLocalStorage = (localStorage: Storage) => {
  return loopLocalStorage(localStorage).sort(
    (item1, item2) => item2.value - item1.value,
  );
};

/**
 * 获得 localStorage 前 k 个 关键词
 * @param localStorage 数据仓库
 * @param k top k
 */
const getLocalStroageTopK = (localStorage: Storage, k: number) => {
  return sortLocalStorage(localStorage).slice(0, k);
};

export { sortLocalStorage, loopLocalStorage, getLocalStroageTopK };
