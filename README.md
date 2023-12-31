#                                            智能协作式众包测试

## 目的

​        IT技术⻜快发展，软件测试的需求也⽇益增加。为了⼤量软件的测试，“众包⼯⼈”这个职业诞⽣。众包 ⼯⼈可以对于发包⽅发布的软件测试任务，进⾏离线的测试，最后将测试的结果反馈给发包⽅。 发包⽅和众包⼯⼈需要⼀个连接更紧密的平台，架起他们之间交流隔阂的桥梁。 该产品为平台增加智能性，通过批注、评分、评论机制搭建完整的协作机制，提⾼众包测试效果，通 过推荐算法实现个性化，提⾼众包测试的效率。

- **传统测试**

  - 软件产品迭代快速、运行环境碎片化
  - 传统测试测试周期长，测试环境单一

- **众包测试**

  - 召集大量众包工人，以软件用户的身份在线完成测试任务
  - 对复杂真实应用场景和真实用户表现的良好模拟
  - 测试周期短，测试成本低

- **协作式众包测试**
  
  - 工人群体智能汇聚
  - 测试过程评价机制
  - 测试结果聚合优化
  
  ### 推荐算法（暂定这两个，管理员可以自定义添加）
  
  -  采⽤⽤户协同过滤算法，计算⽬标⽤户与其他⽤户参与任务的相似性，选取前n个最相似的⽤户参 与的任务作为推荐推送给⽬标⽤户 
  
  -  采⽤基于⽤户特征的推荐算法，根据⽬标⽤户选择的任务偏好以及真实的承接与完成任务情况计算⽬标⽤户特征值，根据⽬标⽤户与其他⽤户特征的相似性，选选取前n个最相似的⽤户参与的任务 作为推荐推送给⽬标⽤户
  
    #### 特别说明
  
    ​        当采取⽤户协同过滤推荐⽅式时，若众包⼯⼈尚未参加任何任务，或其他众包⼯⼈均未参加任何任务， 则推荐算法⽆法有效⼯作(特定情况），此时任务⼴场以随机的⽅式展示满⾜筛选条件的任务。否则，依据该众包⼯ ⼈与其他众包⼯⼈参与任务的相似程度，有序地返回推荐的任务（推荐度由⾼到低排序）给该众包⼯ ⼈。若此时满⾜筛选条件的任务数量过多（暂定量为500），则从所有候选任务中随机抽取500条任务， 计算推荐度（此时任务推荐具有⼀定的随机性）。 当采取⽤户特征推荐⽅式时，以上图为例，当众包⼯⼈选择windows平台作为⾃⼰的偏好平台，且其他 选择windows偏好的众包⼯⼈参与“windows测试“的任务，则在查询任务时，优先将该任务推荐给该众 包⼯⼈（排在前列）。同⽤户协同过滤推荐⽅式，若此时其他众包⼯⼈均未参加任何任务，此推荐算法 同样⽆法有效⼯作，任务⼴场以随机的⽅式展示满⾜筛选条件的任务。
  
  ### 其他
  
  ⽂字相似度。图像相似度识别算法待定
  
  总报告相似度通过⽂字相似度和图像相似度加权计算（具体权值计算公式待定）

1. ## 预期功能

   1. 众包工人在对任务进行查询时将被推送根据其自身属性定制化推荐的任务
   2. 众包工人可对其他工人提交的报告进行评分以及评论
   3. 众包工人对同一测试任务可进行协作
   4. 管理员可以调整应用于众包工人的推荐算法
   5. 发包方可以发布任务
   6. 众包工人可以接受任务
   7. 众包工人可以提交并修改报告
   8. 发包方可以查看众包工人提交的报告，并查看所有报告的智能分类。

   ## 产品功能

   ### 所有用户相关

   - 用户可以注册并选择身份
   -  用户可以登录并获得更多权限
   -  用户可以浏览发布的所有任务

   ### 众包工人相关

   - .众包工人可以选择自身特征，并被推送根据其自身特征推荐的任务
   - 系统可以依据其他参与的任务为目标众包工人推送任务
   - 系统可以让众包工人在提交报告后查看与其报告相似或质量差的报告并展开协作
   - 众包工人可以对其他工人的报告进行评分与评论
   - 众包工人可以对其他工人的报告进行批注
   - 众包工人可以让查看其他工人的评价与批注对自身报告进行修改
   - 可以让众包工人领取任务并下载任务相关文件
   - 可以让众包工人在线填写并提交测试报告(系统会检测报告格式)

   ### 发包方相关

   - 发包方可以查看众包工人提交的最新报告和历史版本报告
   - 发包方可以查看任务下的相似报告
   - 发包方可以发布新任务并设置任务属性
   - 发包方可以为任务上传相关文件
   - 发包方可以修改已发布的任务
   - 发包方可以查看系统智能报告分类结果

   ### 管理员相关

   - 管理员可以修改任务推荐算法

   ### 用户特征

   - 众包工人：在平台领取任务、离线测试并提交所发现的问题报告、通过批注、评分、留言与其他众包工人展开协作
   - 发包方：在平台发布任务并查看提交的报告以确定产品缺陷
   - 管理员：管理平台运行，调整用户推荐策略

## 详细需求描述

### 所有用户-浏览所有任务

#### 特性描述

所有用户都可以在任务广场浏览所有任务

优先级：高

####  功能需求

用户通过登陆系统跳转到任务广场，并且显示出所有任务的信息，用户点击某个具体的任务，系统会显示该任务的具体信息

任务⼴场为web主⻚，管理员展示的所有任务是不经过推荐算法的，众包⼯⼈展示的任务会根据其属性进⾏算法推荐。

###  众包⼯⼈-设置⾃身属性

####  特性描述

众包⼯⼈在第⼀次登陆时可以设置⾃身任务偏好 

优先级：低

####  功能需求

众包⼯⼈第一次登录，系统显示任务偏好类型并提示⽤户选择。

任务偏好类型：

- ⽤户可以选择任务的平台偏好 
- ⽤户可以选择任务的类型偏好

###  众包⼯⼈-浏览推荐任务

#### 特性描述

众包⼯⼈可以浏览定制化推荐的任务 

优先级：⾼

#### 功能需求

系统跳转到任务⼴场，并且显示根据⽤户属性⾃动推荐的任务信息 。

众包⼯⼈点击某个具体的任务，系统会显示该任务的具体信息

###  众包⼯⼈-领取任务

#### 特性描述

众包⼯⼈可以在任务详情界⾯领取任务 

优先级：低

#### 功能需求

众包⼯⼈点击领取任务，系统根据剩余⼈数判断是否可以领取并提示。

众包⼯⼈在任务⼴场中选择任务，点击详情后可以领取任务，如果任务剩余⼈数⾜够则将任务加⼊⾄我 的任务列表中。

###  众包⼯⼈-提交报告

####  特性描述

众包⼯⼈可以提交报告 

优先级：⾼

#### 功能需求

众包⼯⼈可以选择查看所有⾃⼰领取的任务，系统显示所有⽤户⾃⼰领取的任务 。众包⼯⼈选择其中任何⼀个任务 。众包⼯⼈在我的任务列表中选择任务，点击详情后可以查看任务的详细信息，并编辑⾃身报告，填写报 告详细信息后可以提交⾃身报告。

###  发包⽅-发布新任务

#### 特性描述

发布⽅可以发布新任务 

优先级：⾼

#### 功能需求

发包方可以输⼊任务名称，任务最⼤⼈数 ，任务标签 ，任务截⽌⽇期，任务简介 ，任务介绍等相关信息。

前端会对传⼊的⽂件按提示内容进⾏⼀定拦截，测试⽬标⽂件格式会根据选择设备类型进⾏变化。

### 发包⽅-查看其发布任务中众包⼯⼈上传的报告

#### 特性描述

发包⽅可以查看其所发布任务中众包⼯⼈上传的报告 

优先级：中

#### 功能需求

发包方可以选择查看所有⾃⼰发布的任务，系统显示该任务有关的所有众包⼯⼈发布的最新报告详情， 

系统显示该报告的历史版本，发包方能够查看报告的相似度 ，可以看到参与该⽤户报告协作的所有⽤户。

### 发包⽅-修改已发布的任务

#### 特性描述

发包⽅可以修改⾃⼰已发布的任务 

优先级：低

#### 功能需求

发包方能够选择查看所有⾃⼰发布的任务，系统显示该任务的具体信息，发包方可以选择修改其信息，并确认修改后在后台更新。

发包⽅在我的任务中选择已发布的任务并点击任务详情，此时可以选择编辑任务对任务信息进⾏修改， 修改任务时不能使需求⼈数⼩于当前参与⼈数。

### 发包⽅-查看系统智能分类报告结果

#### 特性描述

可以让发包⽅查看某任务的所有报告分类结果，该结果由系统智能⽣成。

#### 功能需求

发包⽅点击查看报告界⾯，系统将分类完的报告按照类别呈现给⽤户，且能够展示具体类别的报告

###  管理员-修改推荐算法

#### 特性描述

可以让管理员修改任务推荐算法

 优先级：低

#### 功能需求

- 管理员可以定义采⽤的推荐算法
- 管理员可以定义部分推荐算法使⽤的属性

（暂定）默认开启⽤户协同过滤策略，管理员可以选择具体执⾏哪种策略，并定义⽤户特征相似度推荐的策略
