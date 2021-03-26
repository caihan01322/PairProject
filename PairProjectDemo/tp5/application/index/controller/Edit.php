<?php
namespace app\index\controller;

use think\Controller;
use think\Db;

class Edit extends Controller
{
    
    public function index()
    {
        $page = 0;
        $limit = 4;
        $data = Db::table('paper')->order('title')->limit($page*$limit,$page*$limit+$limit)->select();
        $count = Db::table('paper')->count();
        $this->assign('tableData',json_encode($data));
        $this->assign('count',$count);
        return $this->fetch();
    }
    
}
