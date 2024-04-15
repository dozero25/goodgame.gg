

const qnaList = [
  {
    q: '1. 게임을 할 때 나에게 채팅이란?',
    a: [
      { answer: 'a. 상대 팀의 멘탈을 부수기 위한 나의 무기', type: [1, 2, 4, 9] },
      { answer: 'b. 사소한 것이라도 소통을 해야 게임 할 맛이 나지.', type: [0, 3, 6, 5, 10, 8] },
      { answer: 'c. 최소한의 말만 하고 채팅으 거의 하지 않는다.', type: [7, 11 ] },
      // { answer: 'd. ???? ', type: [12 ] },
    ]
  },
  {
    q: '2. 솔랙 픽창에서 서폿이 다른 라인을 잡았는데, 서폿이 걸렸다고 라인 스왑을 해달라고 찡찡거린다. 스왑을 안 하면 던진다고 하는데... 그때 나는?',
    a: [
      { answer: 'a. 팀들에게 스왑 가능하냐고 같이 물어봐주고, 정 안되면 내 라인을 양보해준다.', type: [0, 3, 2, 8] },
      { answer: 'b. 아ㅋㅋ벌써 할 맛 떨어지네. 채팅으로 화를 내고, 던지든 말든 양보는 안된다.', type: [1, 6, 5, 10] },
      { answer: 'c. 입꾹닫. 알아서 하겠지 하고 조용히 내 라인대로 픽을 한다.', type: [7, 4, 9, 11] },
      // { answer: 'd. ???? ', type: [12 ] },
    ]
  },
  {
    q: '3. 한타 승리 후 아는 포탑을 밀려고 했지만, 팀들은 바론을 간다. 아무리 봐도 포탑을 미는 게 맞는 거 같은데...?',
    a: [
      { answer: 'a. 바론을 같이 안 가면 정치의 대상이 될 거 같아서 분위기상 포탑을 바론을 같이 가준다.', type: [1, 3, 2, 10, 8] },
      { answer: 'b. 포탑을 밀어야 되는 이유를 간단 명료하게 설명하고 포탑을 민다..', type: [7, 9, 11] },
      { answer: 'c. 다들 바론 가니깐 바론이 맞겠디? 한타가 이기기도 했고 팀에게 맞춰준다.', type: [0, 6, 5, 4] },
      // { answer: 'd. ???? ', type: [12 ] },
    ]
  },
  {
    q: '4. 게임이 시작되었는데, 로딩이 오래 걸릴 때 나의 행동은?',
    a: [
      { answer: 'a. 일시 정지 해 놨던 유튜브도 보고.. 뤱툰도 좀 보고..느린 컴퓨터를 탓하며 기다리기 바쁘다.', type: [1, 2, 4 ] },
      { answer: 'b. 조합을 보며 어떤 식으로 플레이를 해야 할까 고민이다.', type: [7, 10, 8, 9, 11 ] },
      { answer: 'c. 로딩 창의 상대 숙련도와 챔피언 일러스트들을 보며 감상한다.', type: [0, 3, 6, 5 ] },
      // { answer: 'd. ???? ', type: [12 ] },
    ]
  },
  {
    q: '5. 나는 못 했지만 같은 고수에게 캐리 당해서 꽁승을 했다. 그때 나는?',
    a: [
      { answer: 'a. 상대 팀에게 "너무 상심하지마~ 상대가 나잖아~ 점수 자알 가져갑니다^^"를 시전한다.', type: [1, 7, 10, 4, 9 ] },
      { answer: 'b. 또 만났으면 하는 바람을 가지고 빠르게 큐를 돌린다.', type: [0, 3, 6, 11]},
      { answer: 'c. 저 진짜 반했는데 혹시 듀오 가능할까요? 이번엔 제대로 할 수 있습니다ㅠ', type: [2, 5, 8] },
      // { answer: 'd. ???? ', type: [12 ] },
    ]
  },

  {
    q: '6. 새로운 챔피언이 출시해서 한번 플레이 해보려 한다. 그때 나는?',
    a: [
      { answer: 'a. "오 신!!" 무작정 해본다.', type: [4, 9, 11 ] },
      { answer: 'b. 챔피안 스킬 셋만 흝어보고 일단 해보고 이해한다.', type: [0, 3, 6, 10 ] },
      { answer: 'c. 스킬 공략 법을 최대한 찾아보고 이해하고 해본다.', type: [1, 7, 2, 5, 8 ] },
      // { answer: 'd. ???? ', type: [12 ] },
    ]
  },
  {
    q: '7. 마지막 한 번의 순간으로 승리와 패배가 갈리는 상황. 하지만 친구가 순간 실수를 해서 결국 패배를 하였다. 그때 나의 반응은?',
    a: [
      { answer: 'a. 게임에서 진 것에 분해 친구한테 "아 뭐하냐고!!" 라고 한다.', type: [1, 7, 11 ] },
      { answer: 'b. "아 그떄 너가 거기서 ㅇㅇ했었으면 몰랐을텐데" 문제를 짚어주고 다름판에 잘 하자고 한다.', type: [2, 4, 9 ] },
      { answer: 'c. "ㄱㅊㄱㅊ 그럴 수 있지" 먼저 친구 기분이 상하지 않도록 위로한다.', type: [0, 3, 6, 5, 10, 8 ] },
      // { answer: 'd. ???? ', type: [12 ] },
    ]
  },
  {
    q: '8. 오늘 나의 전적이 빨갛게 물들어 있다..',
    a: [
      { answer: 'a. "언제까지 이러나 한 번 갈 때 까지 가보자." 끝장을 본다.', type: [0, 4, 9 ] },
      { answer: 'b. 여기서 한 판만 더 해보고 안되면 진짜 그만 해야겠다.', type: [3, 2, 6, 5, 10, 8 ] },
      { answer: 'c. 다른 게임도 좀 하고 잠깐 쉬었다가 한다.', type: [1, 7, 11 ] },
      // { answer: 'd. ???? ', type: [12 ] },
    ]
  },
  {
    q: '9. 상대가 피가 적은 상태로 타워 안에 있다. 당신의 선택은?',
    a: [
      { answer: 'a. 이런 저런 변수가 있으니 그냥 안정적으로 플레이 하자', type: [7, 0, 5, 9 ] },
      { answer: 'b. 부쉬에서 라인 돌아 올때를 노려서 잡아보자', type: [1, 3, 6, 11 ] },
      { answer: 'c. 저건 잡아야지! 그냥 무지성 다이브', type: [2, 10, 8, 4 ] },
      // { answer: 'd. ???? ', type: [12 ] },
    ]
  },
  {
    q: '10. 라인 전 중 웨이브가 몰려와서 라인을 받아 먹어야 하는 상황인데 미드와 정글끼리 교전이 일어났다. 합류를 하면 우세를 가져갈 수 있을 거 같지만 확실하지 않다, 그때 나는?',
    a: [
      { answer: 'a. 이러다 망한 게 한두 번이 아니야~ 빽핑을 찍고 라인을 받아 먹는다,', type: [4, 9, 11] },
      { answer: 'b. 인생은 내 맘대로. 합류는 하되 잡을 거 다 잡고 간다.', type: [0, 2, 6, 5 ] },
      { answer: 'c. 조금이라도 가능성이 보이면 라인을 포기하고 팀을 믿어보고 합류한다.', type: [1, 7, 3, 10, 8 ] },
      // { answer: 'd. ???? ', type: [12 ] },
    ]
  },
  {
    q: '11. 친구가 기분이 안 좋다고 갑자기 듀오를 하자고 한다. 나의 반응은?',
    a: [
      { answer: 'a. "롤 들어와"', type: [1, 4, 9, 11 ] },
      { answer: 'b. "기분이 왜 안 좋아? 기분 안 좋으면 롤 말고 다른 거 하는 게 낫지 않나?', type: [7, 0, 3, 6, 10] },
      { answer: 'c. "? 기분이 안 좋은데 듀오는 왜 하자 함?"', type: [2, 5, 8 ] },
      // { answer: 'd. ???? ', type: [12 ] },
    ]
  },
  {
    q: '12. 게임에서 승리를 했지만, 내 계획대로 흘러가지 않은 찝찝한 승리다. 그때 나는?',
    a: [
      { answer: 'a. "아 화나네. 확실하게 간다." 다시 게임을 돌린다.', type: [3, 6, 4, 9 ] },
      { answer: 'b. "아 그때 내가 그렇게 했으면 진작 끝나는 게임이었는데"하며 되생각한다.', type: [0, 2, 5, 10, 8] },
      { answer: 'c. "뭐 어때 점수 달달하구만"', type: [1, 7, 11] },
      // { answer: 'd. ???? ', type: [12 ] },
    ]
  }
]

const infoList = [
  {
    name: '모험을 즐기는 사업가, 수완좋은 활동가 <1>',
    desc: '현실적인 문제해결에 능하며 적응력이 강하고 관용적이다. 사실적이고 관대하며, 개방적이고 사람이나 일에 대한 선입관이 별로 없다. 강한 현실감각으로 타협책을 모색하고 문제를 해결하는 능력이 뛰어나다. 적응을 잘 하고 친구를 좋아하며 긴 설명을 싫어하고, 운동, 음식, 다양한 활동 등 주로 오감으로 보고, 듣고, 만질 수 있는 생활의 모든 것을 즐기는 형이다. 순발력이 뛰어나며 많은 사실들을 쉽게 기억하고, 예술적인 멋과 판단력을 지니고 있으며, 연장이나 재료들을 다루는 데 능숙하다. 논리 분석적으로 일을 처리하는 데 반해, 추상적인 아이디어나 개념에 대해서는 별로 흥미가 없다'
  },
  {
    name: '청렴결백한 논리주의자, 세상의 소금 형이다 <2>',
    desc: '신중하고 조용하며 집중력이 강하고 매사에 철저하며 사리분별력이 뛰어나다. 실제 사실에 대하여 정확하고 체계적으로 기억하며 일 처리에 있어서도 신중하며 책임감이 강하다. 집중력이 강한 현실감각을 지녔으며 조직적이고 침착하다. 보수적인 경향이 있으며, 문제를 해결하는데 과거의 경험을 잘 적용하며, 반복되는 일상적인 일에 대한 인내력이 강하다. 자신과 타인의 감정과 기분을 배려하며, 전체적이고 타협적 방안을 고려하는 노력이 때로 필요하다. 정확성과 조직력을 발휘하는 분야의 일을 선호한다. '
  },
  {
    name: '이건 이거고 저건 저거지 ,엄격한 관리자,사업가형 <3>',
    desc: '한 번 좋아하는 사람이 있으면 내 사람으로 만드는 당신! 호랑이에 해당하는 당신은 썸부터 사랑까지 한 번에 직진하는 스타일이랍니다. 연애할 때 주로 이끄는 성향을 가지고 있고, 시원시원한 성격과 유쾌한 성격을 가지고 있어요. 대쪽같은 성격에다 실수를 용납하는 대인배지만 유혹에 약하고 허영때문에 들뜨는 성격이라 다른 이성의 접근에 주의해야 돼요. 또한 내가 한 말로 인해 상대방이 상처를 받을 수 있기 때문에, 항상 말 조심이 필요하답니다!'
  },
  {
    name: '독특한 매력의 논리적인 사색가, 아이디어뱅크형 <4>',
    desc: '조용하고 과묵하며 논리와 분석으로 문제를 해결하기 좋아한다. 과묵하나 관심이 있는 분야에 대해서는 말을 잘하며 이해가 빠르고 높은 직관력으로 통찰하는 재능과 지적 호기심이 많다. 개인적인 인간관계나 친목회 혹은 잡담 등에 별로 관심이 없으며 매우 분석적이고 논리적이며 객관적 비평을 잘 한다. 사교성이 결여되기 쉬운 경향이 있고, 때로는 의도가 아니더라도 자신의 능력을 과시하는 수가 있기 때문에 거만하게 보일 수 있다.'
  },
  {
    name: '자유로운 영혼의 연예인, 사교적인 유형 <징크스>',
    desc: '사교적이고 활동적이며 수용력이 강하고 낙천적이다. 어떤 상황이든 잘 적응하며 현실적이고 실제적이다. 주위의 사람이나 일어나는 일에 대하여 관심이 많으며 사람이나 사물을 다루는 사실적인 상식이 풍부하다. 때로는 조금 수다스럽고, 깊이가 결여되거나 마무리를 등한시하는 경향이 있으나, 어떤 조직체나 공동체에서 밝고 재미있는 분위기 조성 역할을 잘한다. 야망이 커, 크게 성공할 확률이 높고 리더십이 매우 뛰어나다. 다만 높은 이상에 비해서 현실이 자신의 마음에 들지 않으면 허세를 부리거나 사람들을 무시하는 경우가 많다.'
  },
  {
    name: '성인군자형, 호기심 많은 예술가형 <5>',
    desc: '말없이 다정하고 온화하며 사람들에게 친절하고 겸손하다. 양털 안감을 놓은 오버코트처럼 속마음이 따뜻하다. 그러나 상대방을 잘 알게 될 때까지 이 따뜻함을 잘 드러내지 않는다. 동정적이며 자기 능력에 대해 모든 성격 유형 중에서 가장 겸손하고 적응력과 관용이 많다. 자신의 의견이나 가치를 타인에게 강요하지 않으며 반대의견이나 충돌을 피하고, 인화를 중시한다. 인간과 관계되는 일을 할 때 자신과 타인의 감정에 지나치게 민감하고, 결정력과 추진력이 필요할 때가 많을 것이다.'
  },
  {
    name: '사교적인 외교관, 친선도모형. <6>',
    desc: '동정심이 많고 다른 사람에게 관심을 쏟고 인화를 중시한다. 타고난 협력자로서 동료애가 많고 친절하며 능동적인 구성원이다. 이야기하기를 즐기며 정리 정돈을 잘하고 참을성이 많고 다른 사람들을 잘 도와준다. 사람을 다루고 행동을 요구하는 분야, 예를 들면 교직, 성직, 판매 특히 동정심을 필요로 하는 간호나 의료 분야에 적합하다. 일이나 사람들에 대한 문제에 대하여 냉철한 입장을 취하기 어려워한다. 반대 의견에 부딪혔을 때나 제 요구가 거절당했을 때 마음의 상처를 받는다.'
  },
  {
    name: '선의의 옹호자, 예언자 유형 <7>',
    desc: '인내심이 많고 통찰력과 직관력이 뛰어나며 양심이 바르고 화합을 추구한다. 창의력이 뛰어나며, 강한 직관력으로 말없이 타인에게 영향력을 끼친다. 독창성과 내적 독립심이 강하며, 확고한 신념과 열정으로 자신의 영감을 구현시켜 나가는 정신적 지도자들이 많다. 한 곳에 몰두하는 경향으로 목적 달성에 필요한 주변적인 조건들을 경시하기 쉽고, 자기 안의 갈등이 많고 복잡하다. 이들은 풍부한 내적인 생활을 소유하고 있다.'
  },
  {
    name: '만능 재주꾼, 백과사전형 <8>',
    desc: '조용하고 과묵하고 절제된 호기심으로 인생을 관찰하며 상황을 파악하는 민감성과 도구를 다루는 뛰어난 능력이 있다. 말이 없으며, 객관적으로 인생을 관찰하는 형이다. 필요 이상으로 자신을 발휘하지 않으며, 일과 관계되지 않는 이상 어떤 상황이나 인간관계에 직접 뛰어들지 않는다. 가능한 에너지 소비를 하지 않으려 하며, 사람에 따라 사실적 자료를 정리, 조직하길 좋아하며 기계를 만지거나 인과관계나 객관적 원리에 관심이 많다. 민첩하게 상황을 파악하는 능력이 있다. 느낌이나 감정, 타인에 대한 마음을 표현하기 어려워한다.'
  },
  {
    name: '용의주도한 전략가, 과학자형 / 건축가형 <9>',
    desc: '대부분의 사항에 마땅한 근거를 논해야 하는 ‘합리적 성향’이 강하다. 사람을 사귈 때는 상대방이 해당 분야에 대해 ‘유능한지’와 ‘객관적인지’를 먼저 헤아리려고 한다. 이들은 주로 나무보다 숲을 먼저 보고 일을 처리하는 편이지만, 그 해결법 발상마저 근본적이고 엄밀하기로 유명하다. 이 탓에 예외 상황이나 반칙은 물론, 자기 분야(전공)에 대해 얕은 지식으로 잡담 떠는 걸 몹시 싫어하는 성격으로 알려져 있다. 그 밖에 논리, 독립(혹은 독창성), 효율이라는 가치는 이들에게 상당히 중요하기에, 학문을 좋아하며 수험에도 특화되어 있다고 알려져 있다. 다만, 징험이나 방법론을 못 버리는 특성도 있으므로 아주 새로운 상황(신유형)이나 손쉬운 잡무에 유달리 빈약한 특성을 보이기도 한다. '
  },
  {
    name: '열정적인 중재자, 잔 다르크형 <10>',
    desc: '정열적이고 충실하며 목가적이고, 낭만적이며 내적 신념이 깊다. 마음이 따뜻하고 조용하며 자신이 관계하는 일이나 사람에 대하여 책임감이 강하고 성실하다. 이해심이 많고 관대하며 자신이 지향하는 이상에 대하여 정열적인 신념을 가졌으며, 남을 지배하거나 좋은 인상을 주고자 하는 경향이 거의 없다. 완벽주의적 경향이 있으며, 노동의 대가를 넘어서 자신이 하는 일에 의미를 찾고자하는 경향이 있으며, 인간 이해와 인간 복지에 기여할 수 있는 일을 하기를 원한다. 자신의 이상과 현실이 안고 있는 실제 상황을 고려하는 능력이 필요하다. '
  },
  {
    name: '용감한 수호자형 <11>',
    desc: '조용하고 차분하며 친근하고 책임감이 강하다. 또 온정적이며 침착하고 인내력이 강하다. 조용하지만 본인이 나름대로 존경하거나 좋아하는 사람에 대한 충성심과 애정이 특히 많다. 친구나 지인으로써도 가장 믿음직스러운 성격이다. I 유형이므로 내향형이지만, 내향형 성격 중에서는 외향성이 가장 강하다. 본인의 감정을 파악하는 덴 능숙하지만 감정을 표현하는 덴 서툴다. 본인의 감정이나 노력, 관심등을 표현해도 타인이 알아채지 못하는 경우가 많으며 이러한 상황을 알지 못하는 ISFJ는 타인과의 관계에서 상당한 걱정을 한다. 일 처리에 있어서는 현실 감각을 가지고 실제적이고 조직적이며 완벽주의적으로 처리한다.'
  },
  {
    name: '그냥 <유미>',
    desc: '그냥 유미'
  },
]
