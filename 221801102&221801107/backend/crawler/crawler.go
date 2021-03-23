package crawler

// It's a query crawler that release users from searching in batch.
// The crawler use database to cache the contents.
//
// The SearchJob struct mm-caches the ids of db-cached search results
// The SearchJobs stores "query(key) <-> ids(value)" in a map,
// the key is actually the digest(use MD5) of the queries, which is jointed by
// "|" after sorted to ensure it's uniqueness.

type SearchJob struct {
	total    int
	ids      []uint
	finished bool // is the job finished
}

var searchJobs map[string]SearchJob

func init() {
	searchJobs = make(map[string]SearchJob)
}

func Search(q []string, page int) (ids []uint, total int) {
	return []uint{}, 0
}

func startSearch() {

}
