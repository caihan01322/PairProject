package models

type Relation struct {
	Model
	PaperID int `json:"paper_id"`
	KeyID   int `json:"key_id"`
}

func AddRelation() bool {

	return true
}
